package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.entity.UserEntity;
import com.repository.UserRepository;

@RestController
public class UserController{


	@Autowired UserRepository userepo;
	
	@PostMapping("signup")
	public String SignUp(@RequestBody UserEntity user)
	{
		userepo.save(user);
		return "signup done";
	}
	
	@PostMapping("login")
    public String login(@RequestBody UserEntity loginRequest) {
        UserEntity user = userepo.findByEmail(loginRequest.getEmail());

        if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
            return "login success";
        } else {
            return "invalid email or password";
        }
    }

	
}
