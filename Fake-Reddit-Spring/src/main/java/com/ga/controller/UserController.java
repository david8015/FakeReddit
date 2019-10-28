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

	//Injects UserService
	@Autowired
	UserService userService;

	//@RequestBody is used to serialize/deserialize  object
	//@PathVariable is used to serialize/deserialize instance variable
	//@PostMapping is used for a POST request
	//@GetMapping is used for a GET request
	//@DeleteMapping is used for a DELETE request

	//calls signup which returns a JWT token that is encoded with a username
	//takes in username, email, password from user object
	@PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
            return ResponseEntity.ok(new JwtResponse(userService.signup(user))); }

	//calls login which returns a Response object that contains a JWT token and username
	//Response object is self made
	@PostMapping("/login")
	public Response login(@RequestBody User user){
		return userService.login(user);
	}

	//calls getCommentsByUser which returns a list of comments associated with a userId
	@GetMapping("/comments/{userId}")
	public List<Comment> getCommentByUser(@PathVariable Long userId ){
		return userService.getCommentsByUser(userId);
	}

	//ca''s getPostByUser which returns a list of posts associated with a userId
	@GetMapping("/posts/{userId}")
	public List<Post> getPostsByUser(@PathVariable Long userId ){return userService.getPostsByUser(userId);}
}
