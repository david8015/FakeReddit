package com.ga.controller;

import com.ga.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ga.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;

	@PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
            return ResponseEntity.ok(new JwtResponse(userService.signup(user)));
        }

	@PostMapping("/login")
	public Response login(@RequestBody User user){
		return userService.login(user);
	}

	@GetMapping("/comments/{userId}")
	public List<Comment> getCommentByUser(@PathVariable Long userId ){
		return userService.getCommentsByUser(userId);
	}

	@GetMapping("/posts{userId}")
	public List<Post> getPostsByUser(@PathVariable Long userId ){return userService.getPostsByUser(userId);}
}
