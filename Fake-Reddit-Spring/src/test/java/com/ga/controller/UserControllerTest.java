package com.ga.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ga.entity.Post;
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

import com.ga.entity.Comment;
import com.ga.entity.Response;
import com.ga.service.UserService;

import java.util.ArrayList;
import java.util.List;


@RunWith(MockitoJUnitRunner.class) //execute JUnit using Mockito's testing support
public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController; //instructs mockito to create mocks versions of them
    
    @InjectMocks
    Response response;

    @InjectMocks
    Comment comment;

    @InjectMocks
    Post post;

    @Mock
    List<Comment> commentList;

    @Before
    public void init(){
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        post = new Post();
        commentList = new ArrayList<Comment>();
        comment.setId(1L);
        comment.setDescription("test comment1");
        post.addComment(comment);
        post.getComments();
    }

    @Test
    public void getCommentByUserId_Success() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/user/comments/1")
                .accept(MediaType.APPLICATION_JSON);

        when(userService.getCommentsByUser(anyLong())).thenReturn(post.getComments());

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());

    }

    @Test
    public void signup_User_Success() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/user/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createUserInJson("king", "Queen", "king@Email.com"));

        when(userService.signup(any())).thenReturn("2468");

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"token\":\"2468\"}"));

    }

    @Test
    public void login_User_Success() throws Exception{
        response.setToken("king");
        response.setUsername("test");

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createUserInJson("king", "test2434", "king@test.com"));

        when(userService.login(any())).thenReturn(response);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"token\":\"king\",\"username\":\"test\"}"));


    }
    
    private static String createUserInJson(String username, String password, String email){
        return "{\"username\":\"" + username + "\", " +
                "\"password\":\"" + password + "\", " +
                "\"email\":\"" + email + "\"}";
    }


    public static String createResponseInJson(String token, String username){
        return  "{\"token\":\"" + token + "\", " +
                    "\"username\":\"" + username + "\"}";
    }
    

    
    

}
