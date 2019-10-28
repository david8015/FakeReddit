package com.ga.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.mockito.ArgumentMatchers.anyString;

import com.ga.entity.Comment;
import com.ga.entity.Post;
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

import com.ga.entity.User;

import java.util.List;

public class UserDaoTest {
	
	@Rule
	public MockitoRule rule = MockitoJUnit.rule();

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

	@InjectMocks
	private User user;

	@Mock
	private List<Comment> comments;

	@InjectMocks
	private Comment comment;

	@Mock
	private List<Post> posts;

	@InjectMocks
	private Post post;


	@Before
	public void initializeDummyObjects() {
		user.setUserId(1L);
		user.setEmail("test");
		user.setUsername("king");
		user.setPassword("password");
		user.setRole("userrole");
		post.setId(1L);
		post.setTitle("test");
		post.setDescription("description");

		comment.setId(1L);
		comment.setDescription("description");
		comment.setPost(post);

		post.addComment(comment);

		comments.add(comment);
		user.setUserId(1L);
		user.setUsername("username");
		post.setUser(user);
		posts.add(post);


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

	@Test
	public void getUserByEmail_user_success(){
		when(session.createQuery(anyString())).thenReturn(query);
		when(query.getSingleResult()).thenReturn(user);
		User searchedUser = userDao.getUserByEmail("test");
		assertEquals(searchedUser, user);
		assertNotNull("Test returned null object, expected non-null", searchedUser);
	}

	@Test
	public void getCommentByUserId_User_Sucess(){
		when(session.get(User.class, 1L)).thenReturn(user);
		List<Comment> comments = userDao.getCommentByUser(1L);
		assertNotNull("Test returned null object, expected non-null", comments);
	}

	@Test
	public void getPostsByUserId_Success(){
		when(session.get(User.class, 1L)).thenReturn(user);
		List<Post> posts = userDao.gePostsByUser(1L);
		assertNotNull("Test returned null object, expected non-null", posts);
	}
}
