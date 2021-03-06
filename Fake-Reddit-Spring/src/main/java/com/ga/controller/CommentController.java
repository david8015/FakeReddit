package com.ga.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.service.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {

	//Injects commentService
	@Autowired
	CommentService commentService;

	//@RequestBody is used to serialize/deserialize  object
	//@PathVariable is used to serialize/deserialize instance variable
	//@PostMapping is used for a POST request
	//@PutMapping is used for a PUT request
	//@DeleteMapping is used for a DELETE request

	//calls createComment by taking a postId
	@PostMapping("/{postId}")
	public Comment createComments(@RequestBody Comment comment, @PathVariable Long postId) {
		return commentService.createComment(comment, postId);
	}

	//calls deleteCommentById by taking in a commentId
	@DeleteMapping("/{commentId}")
	public Long deleteByCommentId(@PathVariable Long commentId) {
		return commentService.deleteCommentById(commentId);
	}

	//calls updateComment by taking in a commentId and a comment object (for description)
	@PutMapping("/{commentId}")
	public Comment updateComment(@RequestBody Comment comment, @PathVariable Long commentId) {
		return commentService.updateComment(comment, commentId);
	}
}
