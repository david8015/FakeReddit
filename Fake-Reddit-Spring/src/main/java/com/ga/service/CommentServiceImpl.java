package com.ga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ga.dao.CommentDao;
import com.ga.entity.Comment;
import com.ga.entity.Post;

@Service
public class CommentServiceImpl implements CommentService{

	//Injects commentDao
	@Autowired
	CommentDao commentDao;

	//Injects UserService
	@Autowired
	private UserService userService;
	

	//gets a user from userService layer by calling returnUsername()
	//sends that username, a post id, and a comment to dao layer to create/return a comment
	@Override
	public Comment createComment(Comment comment, Long postId) {
		String username = userService.returnUsername();
		return commentDao.createComment(comment, username, postId);
	}


	//calls commentDao.deleteCommentById by passing it a commentId
	@Override
	public Long deleteCommentById(Long commentId) {
		return commentDao.deleteCommentById(commentId);
	}


	//calls commentDao.updateComment by passing it a commentId and a comment
	@Override
	public Comment updateComment(Comment comment, Long commentId) {
		return commentDao.updateComment(comment, commentId);
	}


}
