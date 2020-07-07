package it.dst.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import it.dst.service.UserService;

@Controller
public class ControllerUtente {
	
	@Autowired
	private UserService userService;
	
	 @GetMapping(value={"/"})
	    public ModelAndView login(){
	        ModelAndView modelAndView = new ModelAndView();
	        modelAndView.setViewName("homepage");
	        return modelAndView;
	    }
}
