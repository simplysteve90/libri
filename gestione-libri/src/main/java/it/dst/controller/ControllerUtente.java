package it.dst.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import it.dst.model.Libro;
import it.dst.model.User;
import it.dst.service.LibroService;
import it.dst.service.UserService;

@Controller
public class ControllerUtente {
	@Autowired
	private UserService userService;
	
	@Autowired
	private LibroService libroService;
	
	 @GetMapping(value={"/", "/login"})
	    public ModelAndView login(){
	        ModelAndView modelAndView = new ModelAndView();
	        User user = new User();
	        modelAndView.setViewName("homepage");
	        modelAndView.addObject("user", user);
	        return modelAndView;
	    }
	 
	   @GetMapping(value="/registrazione")
	    public ModelAndView registrazione(String azione){
	        ModelAndView modelAndView = new ModelAndView();
	        if("registrazione".equals(azione)) {
	        User user = new User();
	        modelAndView.addObject("user", user);
	        modelAndView.setViewName("registrazione");
	        return modelAndView;
	        }
	        modelAndView.addObject("listaLibri", libroService.listaLibri());
	        modelAndView.setViewName("vediLibri");
	        return modelAndView;
	    }
	   @PostMapping(value="/registrazione")
	    public ModelAndView registrazione(@Valid User user, BindingResult bindingResult,@RequestParam("ruolo")String ruolo){
	        ModelAndView modelAndView = new ModelAndView();
	        User userExists = userService.findUserByUsername(user.getUsername());
	        if (userExists != null) {
	            bindingResult
	                    .rejectValue("username", "error.user",
	                            "Utente gia registrato");
	        }
	        if (bindingResult.hasErrors()) {
	            modelAndView.setViewName("registrazione");
	        } else {
	            userService.saveUser(user);
	            modelAndView.addObject("successMessage", "Utente registrato con successo!");
	            modelAndView.addObject("user", new User());
	            modelAndView.setViewName("registrazione");
	        }
	        return modelAndView;
	   }
	   
	   @PostMapping(value="/accessoUtente")
	    public ModelAndView accesso(@Valid User user, BindingResult bindingResult){
	        ModelAndView modelAndView = new ModelAndView();
	        User userExists = userService.findUserByUsername(user.getUsername());
	        if (userExists != null) {
	           modelAndView.setViewName("profiloUtente");
	        }
	        else {
	            modelAndView.addObject("successMessage", "Utente registrato con successo!");
	            modelAndView.addObject("user", new User());
	            modelAndView.setViewName("registrazione");
	        }
	        return modelAndView;
	   }
	   
	   @GetMapping(value="/admin/home")
	    public ModelAndView home(){
	        ModelAndView modelAndView = new ModelAndView();
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        User user = userService.findUserByUsername(auth.getName());
	        modelAndView.addObject("username", "Welcome " + user.getUsername() + "/" + " (" + user.getEmail() + ")");
	        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
	        modelAndView.setViewName("admin/home");
	        modelAndView.addObject("listaLibri", libroService.listaLibri());
	        modelAndView.addObject("libro", new Libro());

	        return modelAndView;
	    }
	   
	   @PostMapping(value={"/admin/creaLibro"})
	    public ModelAndView creaLibro(Libro libro){
	        libroService.save(libro);
	        
	        return home();
	    }
	   
	   @GetMapping(value={"/admin/eliminaLibro/{id}"})
	    public ModelAndView eliminaLibro(@PathVariable("id") Long id){
	        libroService.delete(id);
	        
	        return home();
	    }
}
