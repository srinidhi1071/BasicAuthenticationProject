package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.UserRepository;
import com.example.demo.model.User;

@Component
public class UserDAOService {
	
	
	
	@Autowired
	public UserRepository usrRepo;
	
	@Autowired
	public PasswordEncoder passwordEncoder;
	
	public User addUser(User user)
	{
		User u = new User();
		u.setUsername(user.getUsername());
		u.setId((int)(usrRepo.count()+1));
		u.setRoles(user.getRoles());
		u.setPassword(passwordEncoder.encode(user.getPassword()));
		usrRepo.save(u);
		
		return u;
	}

}
