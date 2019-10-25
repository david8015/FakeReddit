package java.com.ga.controller;

import com.ga.controller.UserController;
import com.ga.entity.Response;
import com.ga.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Request;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.class) //execute JUnit using Mockito's testing support
public class UserControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    UserController userController; //instructs mockito to create mocks versions of them

    @Before
    public void init(){
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void helloworld_HelloWorld_Success() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/user/hello")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World!!"));
    }

    @Mock
    UserService userService;

    @Test
    public void signup_User_Success() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/user/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createUserInJson("king", "Queen", "king@Email.com"));

        when(userService.signup(any())).thenReturn("2468");

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("\token\":\"2468\"}"))
                .andReturn();

    }

    private static String createUserInJson(String username, String password, String email){
        return "{\"username\":\"" + username + "\", " +
                "\"password\":\"" + password + "\", " +
                "\"email\":\"" + email + "\"}";
    }

    @Mock
    Response response;

    @Test
    public void login_User_Success() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createResponseInJson("kingfferf", "test2434"));

        when(userService.login(any())).thenReturn(response);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"token\":\"king\",\"username\":\"test\"}"));

    }

    public static String createResponseInJson(String token, String username){
        return  "{\"token\":\"" + token + "\", " +
                    "\"username\":\"" + username + "\"}";

    }

}
