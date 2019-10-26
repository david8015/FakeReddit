package com.ga.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ga.dao.PostDao;
import com.ga.entity.Post;

public class PostServiceTest {
	@Mock
	PostDao postDao;
	
	@Mock
	private UserServiceImpl userService;

	@InjectMocks
	private PostServiceImpl postService;
	
	@InjectMocks
	private Post post;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Before
	public void initializeObjects() {
		post.setId(1L);
		post.setTitle("test");
		post.setDescription("description");
		
//		response.setToken("2345");
//		response.setUsername(user.getUsername());
	}
	
	@Test
	public void createPost_success() {
		Post expectedPost = new Post();
		expectedPost.setId(1L);
		expectedPost.setTitle("test");
		expectedPost.setDescription("description");
		
	    String username = "username";
	 
	    when(userService.returnUsername()).thenReturn(username);
	    
	    System.out.println(username);
	    when(postDao.createPost(any(), anyString())).thenReturn(expectedPost);
	    
	    assertNotNull("expected not null", expectedPost);
	    assertEquals(post.getId(), expectedPost.getId());
	    
	}
	
	@Test
	public void deletePost_Sucess() {
		Long postId = post.getId();
		
		Long expectedPostId = 1L;
		
		when(postDao.DeletePostByPostId(anyLong())).thenReturn(expectedPostId);
		
		assertNotNull(expectedPostId);
		assertEquals(postId, expectedPostId);
		
	}
	
	@Test
	public void upodatePost_success() {
		Post expectedPost = new Post();
		expectedPost.setId(1L);
		expectedPost.setTitle("test");
		expectedPost.setDescription("description");
		
		when(postDao.updatePost(any(), anyLong())).thenReturn(expectedPost);
		
		assertNotNull(expectedPost);
		
		assertEquals(post.getId(), expectedPost.getId());
	}	
}
