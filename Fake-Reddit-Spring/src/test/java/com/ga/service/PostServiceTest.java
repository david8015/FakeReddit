package com.ga.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.ga.entity.Comment;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ga.dao.PostDao;
import com.ga.entity.Post;
import sun.jvm.hotspot.debugger.linux.x86.LinuxX86CFrame;

public class PostServiceTest {
	@Mock
	PostDao postDao;

	@Mock
	private UserService userService;

	@InjectMocks
	private PostServiceImpl postService;

	@InjectMocks
	private Post post;

	@InjectMocks
	Comment comment;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Before
	public void initializeObjects() {
		post.setId(1L);
		post.setTitle("test");
		post.setDescription("description");

		comment.setId(2L);
		comment.setDescription("description2");
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

		when(postDao.deletePostByPostId(anyLong())).thenReturn(postId);
		Long expectedPostId = postService.DeletePostById(postId);
		
		assertNotNull(expectedPostId);
		assertEquals(postId, expectedPostId);

	}

	@Test
	public void updatePost_success() {

		when(postDao.updatePost(any(), anyLong())).thenReturn(post);

		Post expectedPost = postService.updatePost(post, post.getId());

		assertNotNull("expected not null", expectedPost);

		assertEquals(post.getTitle(), expectedPost.getTitle());
	}

	@Test
	public void listPosts_Success() {
		List<Post> listPosts = new ArrayList<Post>();
		listPosts.add(post);

		when(postDao.listPosts()).thenReturn(listPosts);

		List<Post> expectedList = postService.listPosts();

		assertNotNull("expected not null", expectedList);
		assertEquals(expectedList, listPosts);
	}

	@Test
	public void getCommentsByPostId_Success(){
		List<Comment> commentList = new ArrayList<>();
		commentList.add(comment);

		when(postDao.getCommentsByPostId(anyLong())).thenReturn(commentList);

		List<Comment> expectedList = postService.getCommentsByPostId(anyLong());

		assertNotNull(expectedList);
		assertEquals(commentList, expectedList);

	}
}
