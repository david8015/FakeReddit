package com.ga.dao;

import com.ga.entity.Profile;

public interface ProfileDao {
    public Profile createUserProfile(String username, Profile newProfile);

    public Profile getUserProfile(Long userId);

    public Long deleteProfileById(Long profileId);

    public Profile updateProfile(Profile profile, Long profileId);
}
