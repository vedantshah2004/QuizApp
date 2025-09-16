package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.entity.UserEntity;
import com.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@RestController
public class UserController{


	@Autowired UserRepository userepo;
	
	@PostMapping("signup")
    public String SignUp(@RequestBody UserEntity user) {
        // Ensure default role
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("USER");
        }
        userepo.save(user);
        return "signup done as " + user.getRole();
    }
	
	
	 @PostMapping("login")
	 public String login(@RequestBody UserEntity loginRequest) 
	 {
		 UserEntity user = userepo.findByEmail(loginRequest.getEmail());
	 	if (user != null && user.getPassword().equals(loginRequest.getPassword()))
	 	{
	     	if ("ADMIN".equalsIgnoreCase(user.getRole()))
	     	{
	    	 return "login success - redirect to admin dashboard";
	      	} 
	     	else
	     	{
	           return "login success - redirect to user dashboard";
	      	}
	 	}
	 
	 	else 
	 	{
		 return "invalid email or password";
	 	}
	 }
	 
	 
	 
	 
	 @PostMapping("logout")
	    public String logout(HttpSession session) {
	        // Invalidate the session to log out
	        session.invalidate();
	        return "logout successful";
	    }
}

	
