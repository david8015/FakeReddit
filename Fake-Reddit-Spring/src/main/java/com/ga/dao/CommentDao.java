package com.ga.dao;

import java.util.List;

import com.ga.entity.Comment;



public interface CommentDao {
	public Comment createComment(Comment comment, String username, Long postId);
	public Long deleteCommentById(Long commentId);

}
