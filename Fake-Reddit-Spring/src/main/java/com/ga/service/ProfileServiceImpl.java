package com.ga.service;

import com.ga.dao.ProfileDao;
import com.ga.entity.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProfileServiceImpl implements ProfileService{

    //Injects profileDao bean
    @Autowired
    ProfileDao profileDao;

    //passes a username and Profile to Dao layer to create a profile - returns the profile to the control layer
    @Override
    public Profile createUserProfile(String username, Profile newProfile) {
        return profileDao.createUserProfile(username, newProfile);
    }

    //passes a userId to Dao layer to create get the user's profile - returns the profile to the control layer
    @Override
    public Profile getUserProfile(Long userId) {
        return profileDao.getUserProfile(userId);
    }

    //passes the profileId to the Dao layer to delete a profile - returns the profileId deleted to the control layer
    @Override
    public Long deleteProfileById(Long profileId) {
        return profileDao.deleteProfileById(profileId);
    }

    //passes a profileId and Profile to Dao layer to update a profile - returns the profile to the control layer
    @Override
    public Profile updateProfile(Profile profile, Long profileId) {
        return profileDao.updateProfile(profile, profileId);
    }
}
