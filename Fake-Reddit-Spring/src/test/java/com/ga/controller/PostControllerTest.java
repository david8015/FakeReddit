package com.ga.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.entity.User;
import com.ga.service.PostService;

@RunWith(MockitoJUnitRunner.class)
public class PostControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	PostController postController;

	@InjectMocks
	User user;

	@InjectMocks
	Post post;

	@InjectMocks
	Comment comment;
	
	List<Comment> comments;
	List<Post> posts;

	@Mock
	PostService postService;
	
	

	@Before
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(postController).build();
		String username;

		user = new User();
		username = "someUser";
		post = new Post();
		posts = new ArrayList<Post>();
		comment = new Comment();
		comments = new ArrayList<Comment>();
		comment.setId(1L);
		comment.setDescription("some post");
		comments.add(comment);

		user.setUserId(1L);
		user.setUsername("testuser");
		user.setPassword("testpass");
		post.setId(1L);
		post.setTitle("some title");
		post.setDescription("some description");
		post.setComments(comments);
		posts.add(post);
	}
	@Test
	public void getAllPosts_Posts_Success() throws Exception{
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/post/list")
				.accept(MediaType.APPLICATION_JSON);
		ObjectMapper mapper = new ObjectMapper();
		String listOfPostsMapper = mapper.writeValueAsString(posts);
		when(postService.listPosts()).thenReturn(posts);
		mockMvc.perform(requestBuilder)
			.andExpect(status().isOk())
			.andExpect(content().string(listOfPostsMapper));
	}
	
	     

//	@Test
//	public void getAllPosts_Posts_SUCCESS() throws Exception {
//		RequestBuilder requestBuilder = MockMvcRequestBuilders
//				.get("/post/list")
//				.accept(MediaType.APPLICATION_JSON);
//
//		when(postService.listPosts()).thenReturn(posts);
//
//		mockMvc.perform(requestBuilder).andExpect(status().isOk());
////	            .andReturn();
//	}

}