package com.ga.controller;

import com.ga.entity.Profile;
import com.ga.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    //Injects profileService
    @Autowired
    ProfileService profileService;

    //@RequestBody is used to serialize/deserialize  object
    //@PathVariable is used to serialize/deserialize instance variable
    //@GetMapping is used for a GET request
    //@PostMapping is used for a POST request
    //@PutMapping is used for a PUT request
    //@DeleteMapping is used for a DELETE request

    //calls getProfile to get the profile of the userID passed in
    @GetMapping("/user/{userId}")
    public Profile getProfile(@PathVariable Long userId) {
        return profileService.getUserProfile(userId);
    }

    //calls createUserProfile to create the profile of the user (username) passed in
    @PostMapping("/user/{username}")
    public Profile createUserProfile(@PathVariable String username, @RequestBody Profile profile) {
        return profileService.createUserProfile(username, profile);
    }

    //calls createUserProfile to create the profile of the user (profileId) passed in
    @DeleteMapping("/{profileId}")
    public Long deletePostById(@PathVariable Long profileId) {
        return profileService.deleteProfileById(profileId);
    }

    //calls updateProfile to update the profile of the user (profileId) passed in
    @PutMapping("/{profileId}")
    public Profile updateProfile(@RequestBody Profile profile, @PathVariable Long profileId) {
        return profileService.updateProfile(profile, profileId);
    }
}
