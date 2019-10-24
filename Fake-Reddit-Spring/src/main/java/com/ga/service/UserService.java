package com.ga.service;

import com.ga.entity.Response;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.ga.entity.User;


public interface UserService extends UserDetailsService{
	public String signup(User user);

	//UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
	
	public Response login (User user);

	public String returnUsername();
	
	
}
