package com.ga.controller;

import com.ga.entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ga.entity.JwtResponse;
import com.ga.entity.User;
import com.ga.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/hello")
	public String hello() {
		return "hello word!!";
	}
	
	@PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
            return ResponseEntity.ok(new JwtResponse(userService.signup(user)));
        }
	
//	@PostMapping("/login")
//	   public ResponseEntity<?> login(@RequestBody User user) {
//	       return ResponseEntity.ok(new JwtResponse(userService.login(user)));
//	   }
	@PostMapping("/login")
	public Response login(@RequestBody User user){
		return userService.login(user);
	}
}
