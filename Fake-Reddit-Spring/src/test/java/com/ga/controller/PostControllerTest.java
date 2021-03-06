package com.ga.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.util.ObjectBuffer;
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
	
	private List<Comment> comments;
	private List<Post> posts;

	@Mock
	PostService postService;

	@Before
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(postController).build();
		post = new Post();
		comment = new Comment();
		comments = new ArrayList<Comment>();
		posts = new ArrayList<Post>();

		comment.setId(1L);
		comment.setDescription("some comment");
		comments.add(comment);

		post.setId(1L);
		post.setTitle("some title");
		post.setDescription("some description");
		post.setComments(comments);
		post.addComment(comment);

		posts.add(post);
	}

	@Test
	public void getCommentsByPostId_Post_Success() throws Exception{
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/post/1/comment");
		ObjectMapper mapper = new ObjectMapper();
		String listOfCommentsByPostId = mapper.writeValueAsString(comments);
		when(postService.getCommentsByPostId(anyLong())).thenReturn(comments);
		mockMvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andExpect(content().string(listOfCommentsByPostId));
	}

	@Test
	public void getAllPosts_Posts_Success() throws Exception{
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/post/list")
				.accept(MediaType.APPLICATION_JSON);
		ObjectMapper mapper = new ObjectMapper();
		String listOfPostsMapper = mapper.writeValueAsString(posts);
		System.out.println(listOfPostsMapper);
		when(postService.listPosts()).thenReturn(posts);
		mockMvc.perform(requestBuilder)
			.andExpect(status().isOk())
			.andExpect(content().string(listOfPostsMapper));
	}

	@Test
	public void createPost_Post_Success() throws Exception{
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/post")
				.contentType(MediaType.APPLICATION_JSON)
				.content(createPostInJSon(post.getTitle(),post.getDescription()));
		ObjectMapper mapper = new ObjectMapper();
		String postMapper = mapper.writeValueAsString(post);
		when(postService.createPost(any())).thenReturn(post);
		mockMvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andExpect(content().string(postMapper));
	}
	@Test
	public void updatePost_Post_Success() throws Exception{
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/post/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(createPostInJSon(post.getTitle(), post.getDescription()));

		when(postService.updatePost(any(), anyLong())).thenReturn(post);
		ObjectMapper mapper = new ObjectMapper();
		String postMapper = mapper.writeValueAsString(post);
		mockMvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andExpect(content().string(postMapper));
	}

	@Test
	public void deletePost_Post_Success() throws Exception{
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/post/1");
		when(postService.DeletePostById(anyLong())).thenReturn(1L);
		mockMvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andExpect(content().json("1"));
	}


	public static String createPostInJSon(String title, String description){
		return "{ \"title\": \"" + title + "\", " +
				"\"description\":\"" + description + "\"}";

	}

}
