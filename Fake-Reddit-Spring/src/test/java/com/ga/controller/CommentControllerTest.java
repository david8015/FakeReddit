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
import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.service.CommentService;
import com.ga.service.PostService;
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

@RunWith(MockitoJUnitRunner.class)
public class CommentControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    CommentController commentController;

    @InjectMocks
    Post post;

    @Mock
    CommentService commentService;

    @InjectMocks
    Comment comment;

    List<Comment> comments;

    @Before
    public void init() {

        mockMvc = MockMvcBuilders.standaloneSetup(commentController).build();
        post = new Post();
        comment = new Comment();
        comments = new ArrayList<>();

        comment.setId(1L);
        comment.setDescription("some comment");
        comments.add(comment);

        post.setId(1L);
        post.setTitle("some title");
        post.setDescription("some description");
        post.addComment(comment);
    }

    @Test
    public void createComment_Comment_Success() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/comment/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createCommentInJson(comment.getDescription()));
        when(commentService.createComment(any(), anyLong())).thenReturn(comment);
        ObjectMapper mapper = new ObjectMapper();
        String commentMapper = mapper.writeValueAsString(comment);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(commentMapper));

    }

    @Test
    public void updatePost_Post_Success() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/comment/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createCommentInJson(comment.getDescription()));

        when(commentService.updateComment(any(), anyLong())).thenReturn(comment);
        ObjectMapper mapper = new ObjectMapper();
        String commentMapper = mapper.writeValueAsString(comment);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(commentMapper));
    }

    @Test
    public void deletePost_Post_Success() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/comment/1");
        when(commentService.deleteCommentById(anyLong())).thenReturn(1L);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("1"));
    }

    public static String createCommentInJson( String description){
        return "{\"description\":\"" + description + "\"}";

    }

}
