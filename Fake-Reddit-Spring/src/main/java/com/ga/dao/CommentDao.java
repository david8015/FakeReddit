package com.ga.dao;

import java.util.List;

import com.ga.entity.Comment;
import com.ga.entity.Post;



public interface CommentDao {
	public Comment createComment(Comment comment, String username, Long postId);
	public Long deleteCommentById(Long commentId);
	public Comment updateComment(Comment comment, Long commentId);

}
