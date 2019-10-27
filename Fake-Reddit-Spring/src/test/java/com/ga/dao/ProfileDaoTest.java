package com.ga.dao;

import com.ga.entity.Profile;
import com.ga.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.junit.Before;

;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class ProfileDaoTest {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    Session session;

    @Mock
    Transaction transaction;

    @InjectMocks
    private ProfileDaoImpl profileDao;

    @InjectMocks
    private User user;

    @InjectMocks
    private Profile profile;

    @Mock
    Query<User> query;

    @Mock
    UserDao userdao;

    @Before
    public void init() {
        profile.setProfileId(1L);
        user.setProfile(profile);
        user.setUserId(1L);
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(session.getTransaction()).thenReturn(transaction);
    }
    @Test
    public void createUser_Profile_Success(){

        Profile testProfile = profileDao.createUserProfile("email", profile);
        assertEquals(profile, testProfile);

    }
    @Test
    public void getUserProfile_Profile_Success(){
        when(session.get(User.class, 1L)).thenReturn(user);
        Profile testProfile = profileDao.getUserProfile(1L);
        assertEquals(profile, testProfile);
        assertNotNull(testProfile);
    }
    @Test
    public void deleteProfileById_Profile_Success(){
        when(session.get(Profile.class, 1L)).thenReturn(profile);
        Long deletedProfileId = profileDao.deleteProfileById(1L);
        assertEquals(deletedProfileId, profile.getProfileId());
        assertNotNull(deletedProfileId);
    }
    @Test
    public void updateProfileById_Profile_Success(){
        when(session.get(Profile.class, 1L)).thenReturn(profile);
        Profile updatedProfile = profileDao.updateProfile(profile, 1L);
        assertEquals(profile, updatedProfile);
        assertNotNull(updatedProfile);
    }


}