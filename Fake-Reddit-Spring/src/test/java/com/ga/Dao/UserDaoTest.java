package com.ga.Dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.mockito.ArgumentMatchers.anyString;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.ga.dao.UserDao;
import com.ga.dao.UserDaoImpl;
import com.ga.entity.User;

public class UserDaoTest {
	
	@Rule
	public MockitoRule rule = MockitoJUnit.rule();
	
	@InjectMocks
	User user;
	
	@InjectMocks
	UserDaoImpl userDao;

	
	@Mock
	private SessionFactory sessionFactory;
	
	@Mock
	Session session;
	
	@Mock
     Transaction transaction;
	
	@Mock
	Query<User> query;
	
	@Before
	public void initializeDummyObjects() {
		user.setUserId(1L);
		user.setEmail("test");
		user.setUsername("king");
		user.setPassword("password");
		user.setRole("userrole");
		
		when(sessionFactory.getCurrentSession()).thenReturn(session);
	       when(session.getTransaction()).thenReturn(transaction);
	}
	
	@Test
	public void signup_user_success() {
		User savedUser = userDao.signup(user);
		
		System.out.print(savedUser);
		
		assertEquals(savedUser, user);
	}
	
	@Test
	public void login_user_Success() {
		when(session.createQuery(anyString())).thenReturn(query);
		when(query.getSingleResult()).thenReturn(user);
		
		User savedUser = userDao.login(user);
		
		assertNotNull("expected not null", savedUser);
		assertEquals(savedUser, user);
	}
}
