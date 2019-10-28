package com.ga.controller;

import java.util.List;

import com.ga.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ga.entity.Post;
import com.ga.service.PostService;

@RestController
@RequestMapping("/post")
public class PostController {

	//Injects postService
	@Autowired
	PostService postService;

	//@RequestBody is used to serialize/deserialize  object
	//@PathVariable is used to serialize/deserialize instance variable
	//@GetMapping is used for a GET request
	//@PostMapping is used for a POST request
	//@PutMapping is used for a PUT request
	//@DeleteMapping is used for a DELETE request

	//calls listPosts to gets a list of all posts
	@GetMapping("/list")
	public List <Post> listPosts(){
		return postService.listPosts();
	}

	//calls createPosts to create a new post
	@PostMapping
	public Post createPost(@RequestBody Post post) {
		return postService.createPost(post);
	}

	//calls deletePostByPostId to delete the post whose id was passed in
	@DeleteMapping("/{postId}")
	public Long deletePostById(@PathVariable Long postId) {
		return postService.DeletePostById(postId);
	}

	//calls updatePost to update the post whose id was passed in
	@PutMapping("/{postId}")
	public Post updatePost(@RequestBody Post post, @PathVariable Long postId) {
		return postService.updatePost(post, postId);
	}

	//calls getCommentsByPostId to get a list of comments for the post whose id was passed in
	@GetMapping("/{postId}/comment")
	public List<Comment> getCommentsByPostId(@PathVariable Long postId){
		return postService.getCommentsByPostId(postId);
	}

}
