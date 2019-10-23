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

	@Autowired
	CommentService commentService;
	
	
	@PostMapping("/{postId}")
	public Comment createComments(@RequestBody Comment comment, @PathVariable Long postId) {
		return commentService.createComment(comment, postId);
		
	}
	@DeleteMapping("/{commentId}")
	public Long deleteByCommentId(@PathVariable Long commentId) {
		return commentService.deleteCommentById(commentId);
	}

	@PutMapping("/{commentId}")
	public Comment updateComment(@RequestBody Comment comment, @PathVariable Long commentId) {
		return commentService.updateComment(comment, commentId);
	}
}
