package com.ga.service;

import com.ga.dao.ProfileDao;
import com.ga.entity.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ProfileServiceImpl implements ProfileService{

    @Autowired
    ProfileDao profileDao;

    @Override
    public Profile createUserProfile(String email, Profile newProfile) {
        return profileDao.createUserProfile(email, newProfile);
    }

    @Override
    public Profile getUserProfile(Long userId) {
        return profileDao.getUserProfile(userId);
    }

    @Override
    public Long deleteProfileById(Long profileId) {
        return profileDao.deleteProfileById(profileId);
    }

    @Override
    public Profile updateProfile(Profile profile, Long profileId) {
        return profileDao.updateProfile(profile, profileId);
    }
}
