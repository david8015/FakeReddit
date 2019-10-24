package com.ga.service;

import com.ga.entity.Profile;

public interface ProfileService {
    public Profile createUserProfile(String email, Profile newProfile);

    public Profile getUserProfile(String email);
}
