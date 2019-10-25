package com.ga.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.when;

import com.ga.config.JwtUtil;
import com.ga.dao.UserDao;
import com.ga.entity.User;
import com.ga.service.UserServiceImpl;


public class UserServiceTest {
	@Mock
	UserDao userDao;
	
	@Mock
	JwtUtil jwtUtil;
	
	@Mock
	private PasswordEncoder bCryptPasswordEncoder;
	
	@InjectMocks
	private UserServiceImpl userService;
	
	@InjectMocks
	private User user;
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void signup_Success() {
		String expectedToken = "2468";
		
		user.setUserId(1L);
		user.setEmail("test");
		user.setUsername("username");
		user.setPassword("password");
		user.setRole("userrole");
		
		when(userDao.signup(any())).thenReturn(user);
		when(userDao.getUserByEmail(anyString())).thenReturn(user);
		when(jwtUtil.generateToken(any())).thenReturn(expectedToken);
		when(bCryptPasswordEncoder.encode(user.getPassword())).thenReturn("password");
		
		String actualToken = userService.signup(user);
		
		assertEquals(actualToken, expectedToken);
	}
	
	
}
