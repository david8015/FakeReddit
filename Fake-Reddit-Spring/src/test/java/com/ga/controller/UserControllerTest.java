package com.ga.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ga.entity.Post;
import com.ga.entity.User;
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

    //Initial injections for Mock testing
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

    @InjectMocks
    User user;

    @Mock
    List<Comment> commentList;

    @Mock
    List<Post> postList;

    //Inital objects and tests set up
    @Before
    public void init(){
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        post = new Post();
        user = new User();
        commentList = new ArrayList<Comment>();
        postList = new ArrayList<Post>();
        comment.setId(1L);
        comment.setDescription("test comment1");
        commentList.add(comment);
        post.setId(1L);
        post.setTitle("test title1");
        post.setDescription("test desc1");
        post.addComment(comment);
        user.setComments(commentList);
        postList.add(post);
        user.setPosts(postList);
    }

    //Tests expects a list of serialized comments when sending in a user id
    @Test
    public void getCommentByUserId_User_Success() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/user/comments/1")
                .accept(MediaType.APPLICATION_JSON);

        when(userService.getCommentsByUser(anyLong())).thenReturn(user.getComments());

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());

    }

    //Tests expects a list of serialized posts when sending in a user id
    @Test
    public void getPostsUserId_User_Success() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/user/posts/1")
                .accept(MediaType.APPLICATION_JSON);

        when(userService.getPostsByUser(anyLong())).thenReturn(user.getPosts());

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());

    }

    //Tests expects a jwt token after sending in a user obj that's created in json
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
    //Tests expects a serialized response object after sending in a user obj that's created in json
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
    //Json-ifies objects in strings for parsing
    private static String createUserInJson(String username, String password, String email){
        return "{\"username\":\"" + username + "\", " +
                "\"password\":\"" + password + "\", " +
                "\"email\":\"" + email + "\"}";
    }

    //Json-ifies objects in strings for parsing
    public static String createResponseInJson(String token, String username){
        return  "{\"token\":\"" + token + "\", " +
                    "\"username\":\"" + username + "\"}";
    }
    

    
    

}
