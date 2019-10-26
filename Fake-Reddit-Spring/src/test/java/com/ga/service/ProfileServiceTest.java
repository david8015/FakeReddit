package com.ga.service;

import com.ga.dao.ProfileDao;
import com.ga.entity.Profile;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class ProfileServiceTest {

    @Mock
    ProfileDao profileDao;

    @InjectMocks
    ProfileServiceImpl profileService;

    @InjectMocks
    Profile profile;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void initializeObjects(){
        profile.setProfileId(1L);
        profile.setMobile("222222222");
        profile.setAddress("1 way");
        profile.setAdditionalEmail("test@Email.com");
    }

    @Test
    public void createUserProfile_Profile_Success(){
        String email = "email@email.com";
        Profile expectedProfile = null;

        when(profileDao.createUserProfile(anyString(), any())).thenReturn(profile);

        expectedProfile = profileService.createUserProfile(email, profile);

        assertNotNull(expectedProfile);
        assertEquals(profile, expectedProfile);
    }

    @Test
    public void getUserProfile_Profile_Success(){
        Long userId = 1L;
        Profile expectedProfile = null;


        when(profileDao.getUserProfile(anyLong())).thenReturn(profile);

        expectedProfile = profileService.getUserProfile(userId);

        assertNotNull(expectedProfile);

        assertEquals(profile, expectedProfile);
    }

    @Test
    public void deleteProfileById_Profile_Success(){
        Long userId = 1L;
        Long expectedId = null;


        when(profileDao.deleteProfileById(anyLong())).thenReturn(userId);

        expectedId = profileService.deleteProfileById(userId);

        assertNotNull(expectedId);

        assertEquals(expectedId, userId);
    }

    @Test
    public void updateProfile_Profile_success(){
        Long profileId = profile.getProfileId();

        when(profileDao.updateProfile(any(), anyLong())).thenReturn(profile);

        Profile expectedProfile = profileService.updateProfile(profile, profileId);

        assertNotNull(expectedProfile);

        assertEquals(expectedProfile, profile);

    }
}
