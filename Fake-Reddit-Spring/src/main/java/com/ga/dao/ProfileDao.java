package com.ga.dao;

import com.ga.entity.Profile;

public interface ProfileDao {
    public Profile createUserProfile(String email, Profile newProfile);

    public Profile getUserProfile(String email);

    public Long deleteProfileById(Long profileId);

    public Profile updateProfile(Profile profile, Long profileId);
}
