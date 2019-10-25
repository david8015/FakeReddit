package com.ga.service;

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
import com.ga.entity.Response;
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
	
	@InjectMocks
	private Response response;
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Before
	public void initializeObjects() {
		user.setUserId(1L);
		user.setEmail("test");
		user.setUsername("king");
		user.setPassword("password");
		user.setRole("userrole");
		
		response.setToken("2345");
		response.setUsername(user.getUsername());
	}
	
	
	
	@Test
	public void signup_Success() {
		String expectedToken = "2345";
	
		when(userDao.signup(any())).thenReturn(user);
		when(userDao.getUserByEmail(anyString())).thenReturn(user);
		when(jwtUtil.generateToken(any())).thenReturn(expectedToken);
		when(bCryptPasswordEncoder.encode(user.getPassword())).thenReturn("password");
		
		String actualToken = userService.signup(user);
		
		assertEquals(actualToken, expectedToken);
	}
	
	@Test
    public void signup_UserNotFound_Error() {
    	
    	User tempUser = user;
    	tempUser.setUserId(null);
    	when(userDao.signup(any())).thenReturn(tempUser);

        String token = userService.signup(user);

        assertEquals(token, null);
    }
	
	@Test
	public void login_ReturnsResponse_Success() {
		String expectedToken = "2345";
		
		when(userDao.login(any())).thenReturn(user);
		when(bCryptPasswordEncoder.matches(any(), any())).thenReturn(true);
		when(userDao.getUserByEmail(anyString())).thenReturn(user);
		when(jwtUtil.generateToken(any())).thenReturn(expectedToken);
		when(bCryptPasswordEncoder.encode(user.getPassword())).thenReturn("password");
		
		Response actualResponse = userService.login(user);
		
		System.out.println(response.getToken());
		System.out.println(actualResponse.getToken());
		
		assertEquals(actualResponse.getUsername(), response.getUsername());
		assertEquals(actualResponse.getToken(), response.getToken());
		
		
	}
	
	@Test
	   public void login_UserNotFound_Error() {
	       
	       User tempUser = user;
	       tempUser.setUserId(null);
	       when(userDao.login(any())).thenReturn(null);
	       Response token = userService.login(user);
	       assertEquals(token, null);
	   }
	
	
}
