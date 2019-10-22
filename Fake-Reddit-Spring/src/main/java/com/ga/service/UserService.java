package com.ga.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ga.entity.User;


public interface UserService extends UserDetailsService{
	public User signup(User user);

	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
