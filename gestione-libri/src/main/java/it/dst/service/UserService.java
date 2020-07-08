package it.dst.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import it.dst.model.Role;
import it.dst.model.User;
import it.dst.repository.RoleRepository;
import it.dst.repository.UserRepository;

@Service
public class UserService {
		private UserRepository userRepository;
	    private RoleRepository roleRepository;
	    private BCryptPasswordEncoder bCryptPasswordEncoder;

	    @Autowired
	    public UserService(UserRepository userRepository,
	                       RoleRepository roleRepository,
	                       BCryptPasswordEncoder bCryptPasswordEncoder) {
	        this.userRepository = userRepository;
	        this.roleRepository = roleRepository;
	        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	    }

	    public User findUserByEmail(String email) {
	        return userRepository.findByEmail(email);
	    }

	    public User findUserByUsername(String userName) {
	        return userRepository.findByUsername(userName);
	    }

	    public User saveUser(User user) {
	        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	        user.setActive(true);
	       
	        Role userRole = roleRepository.findByRole("ADMIN");
	        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
	        
	        return userRepository.save(user);
	    }

}
