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
	
	@Autowired
	PostService postService;
	
	@GetMapping("/list")
	public List <Post> listPosts(){
		return postService.listPosts();
	}
	
	@PostMapping
	public Post createPost(@RequestBody Post post) {
		return postService.createPost(post);
		
	}
	@DeleteMapping("/{postId}")
	public Long deletePostById(@PathVariable Long postId) {
		return postService.DeletePostById(postId);
	}

	@PutMapping("/{postId}")
	public Post updatePost(@RequestBody Post post, @PathVariable Long postId) {
		return postService.updatePost(post, postId);
	}
	
	@GetMapping("/{postId}/comment")
	public List<Comment> getCommentsByPostId(@PathVariable Long postId){
		return postService.getCommentsByPostId(postId);
	}

}
