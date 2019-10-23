package com.ga.service;

import java.util.List;

import com.ga.entity.Comment;
import com.ga.entity.Post;
;

public interface CommentService {

	public Comment createComment(Comment comment, Long postId);
	public Long deleteCommentById(Long commentId);
	public Comment updateComment(Comment comment, Long commentId);
	
	
}
