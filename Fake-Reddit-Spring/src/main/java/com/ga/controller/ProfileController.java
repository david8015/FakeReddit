package com.ga.controller;

import com.ga.entity.Profile;
import com.ga.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @GetMapping("/{email}")
    public Profile getProfile(@PathVariable String email) {
        return profileService.getUserProfile(email);
    }

    @PostMapping("/{email}")
    public Profile createUserProfile(@PathVariable String email, @RequestBody Profile profile) {
        return profileService.createUserProfile(email, profile);
    }
    @DeleteMapping("/{profileId}")
    public Long deletePostById(@PathVariable Long profileId) {
        return profileService.deleteProfileById(profileId);
    }

    @PutMapping("/{profileId}")
    public Profile updateProfile(@RequestBody Profile profile, @PathVariable Long profileId) {
        return profileService.updateProfile(profile, profileId);
    }
}
