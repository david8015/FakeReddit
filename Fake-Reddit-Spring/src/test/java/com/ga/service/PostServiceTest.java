package com.ga.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
	private UserService userService;

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
	}

	@Test
	public void createPost_success() {
		String username = "username";
		Post expectedPost = null;

		when(userService.returnUsername()).thenReturn(username);
		when(postDao.createPost(any(), anyString())).thenReturn(post);

		expectedPost = postService.createPost(post);

		assertNotNull("expected not null", expectedPost);
		assertEquals(post.getId(), expectedPost.getId());

	}

	@Test
	public void deletePost_Sucess() {
		Long postId = post.getId();

		when(postDao.DeletePostByPostId(anyLong())).thenReturn(postId);
		Long expectedPostId = postService.DeletePostById(postId);
		
		assertNotNull(expectedPostId);
		assertEquals(postId, expectedPostId);

	}

	@Test
	public void upodatePost_success() {

		when(postDao.updatePost(any(), anyLong())).thenReturn(post);

		Post expectedPost = postService.updatePost(post, post.getId());

		assertNotNull("expected not null", expectedPost);

		assertEquals(post.getTitle(), expectedPost.getTitle());
	}

	@Test
	public void listPosts_Success() {
		List<Post> listPosts = new ArrayList<Post>();
		listPosts.add(post);

		List<Post> expectedList = new ArrayList<Post>();
		expectedList.add(post);

		when(postDao.listPosts()).thenReturn(expectedList);

		assertNotNull("expected not null", expectedList);
//		assertEquals();
	}
}
