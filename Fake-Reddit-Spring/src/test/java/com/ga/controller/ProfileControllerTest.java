package com.ga.controller;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.util.ObjectBuffer;
import com.ga.entity.Profile;
import com.ga.service.CommentService;
import com.ga.service.ProfileService;
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
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@RunWith(MockitoJUnitRunner.class)
public class ProfileControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    ProfileController profileController;

    @InjectMocks
    User user;

    @InjectMocks
    Profile profile;

    @Mock
    ProfileService profileService;

    @Before
    public void init() {

        mockMvc = MockMvcBuilders.standaloneSetup(profileController).build();
        user = new User();
        profile = new Profile();

        user.setUserId(1L);
        user.setEmail("test@email.com");
        user.setUsername("test");

        profile.setProfileId(1L);
        profile.setAddress("test address");
        profile.setMobile("test mobile");
        //profile.setAdditionalEmail("test additional email");

        user.setProfile(profile);
        

    }
    @Test
    public void getProfileByUserId_Profile_Success() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/profile/user/1");
        ObjectMapper mapper = new ObjectMapper();
        String profileByUsername = mapper.writeValueAsString(profile);
        when(profileService.getUserProfile(anyLong())).thenReturn(profile);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(profileByUsername));
    }

    @Test
    public void createProfileByEmail_Profile_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/profile/user/test@email.com")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createProfileInJson(profile.getMobile(), profile.getAddress()));
        when(profileService.createUserProfile(anyString(), any())).thenReturn(profile);
        ObjectMapper mapper = new ObjectMapper();
        String profile2 = mapper.writeValueAsString(profile);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(profile2));

    }

    @Test
    public void updatePost_Post_Success() throws Exception{

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/profile/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createProfileInJson(profile.getMobile(), profile.getAddress()));

        when(profileService.updateProfile(any(), anyLong())).thenReturn(profile);
        ObjectMapper mapper = new ObjectMapper();
        String profile2 = mapper.writeValueAsString(profile);
        System.out.println(profile2);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(profile2));
    }

    @Test
    public void deleteProfile_Profile_Success() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/profile/1");
        when(profileService.deleteProfileById(anyLong())).thenReturn(1L);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("1"));
    }
    public static String createProfileInJson( String mobile, String address){
        return "{ \"mobile\": \"" + mobile + "\", " +
                "\"address\":\"" + address + "\" }";

    }
}
