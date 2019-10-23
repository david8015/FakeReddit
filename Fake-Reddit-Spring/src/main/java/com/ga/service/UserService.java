package com.ga.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ga.entity.User;


public interface UserService extends UserDetailsService{
	public String signup(User user);

	//UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
	
	public String login (User user);

	public String returnUsername();
	
	
}
