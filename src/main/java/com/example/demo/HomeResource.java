package com.example.demo;

import java.security.Principal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserDAOService;

@RestController
public class HomeResource {
	
	@Autowired
	UserDAOService userDaoService;
	
	@GetMapping("/")
	public String home() {
		return("<h1>Welcome My Lord</h1>");
	}
	
	@PostMapping("/")
	public User addUser(@RequestBody User user) {
		
		User u = userDaoService.addUser(user);
		
		return u;
	}
	
	@GetMapping("/user")
	public UserDetails user() {
		
		
		Object userPrincipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails ud = ((UserDetails)userPrincipal);
			return ud;
			
//		String un =ud.getUsername();
//		String pw = ud.getPassword();
//		Collection<? extends GrantedAuthority> role = ud.getAuthorities();
//		return("<h1>Welcome My User</h1>"+"<br>"+"UserName"+"<br />"+un+"Password"+pw+"Role"+role);
	}
	
	@GetMapping("/admin")
	public String admin() {
		
		Object userPrincipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails ud = ((UserDetails)userPrincipal);
		String un =ud.getUsername();
		String pw = ud.getPassword();
		Collection<? extends GrantedAuthority> role = ud.getAuthorities();
		return("<h1>Welcome My Admin</h1>"+"<br>"+"UserName"+"<br />"+un+"Password"+pw+"Role"+role);
		
	}

}
