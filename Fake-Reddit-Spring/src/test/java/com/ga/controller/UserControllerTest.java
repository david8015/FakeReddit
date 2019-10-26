package com.ga.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ga.entity.Comment;
import com.ga.entity.Response;
import com.ga.service.UserService;


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

    @Before
    public void init(){
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
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
    

    
    
//    @Test
//    public void getCommentByUser_Success() throws Exception {
//    	List<Comment> commentList = new ArrayList<Comment>();
//    	comment.setId(1L);
//    	comment.setDescription("test comment1");
//    	commentList.add(comment);
//
//    	RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .get("/user/comments/test@comment.com")
//                .accept(MediaType.APPLICATION_JSON);
//
//    	when(userService.getCommentsByUser(any())).thenReturn(commentList);
//
//    	MvcResult result = mockMvc.perform(requestBuilder)
//					    	.andExpect(status().isOk())
//					    	.andReturn();
//
//    	System.out.println(result.getResponse().getContentAsString());
//
//    }
}
