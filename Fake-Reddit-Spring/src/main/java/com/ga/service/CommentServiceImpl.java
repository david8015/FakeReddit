package com.ga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ga.dao.CommentDao;
import com.ga.entity.Comment;

@Service
public class CommentServiceImpl implements CommentService{
	@Autowired
	CommentDao commentDao;

	@Autowired
	private UserService userService;
	
	
	
	@Override
	public Comment createComment(Comment comment, Long postId) {
		String username = userService.returnUsername();
		return commentDao.createComment(comment, username, postId);
	}



	@Override
	public Long deleteCommentById(Long commentId) {
		return commentDao.deleteCommentById(commentId);
	}


}
