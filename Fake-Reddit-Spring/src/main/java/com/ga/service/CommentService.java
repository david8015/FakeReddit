package com.ga.service;

import java.util.List;

import com.ga.entity.Comment;
;

public interface CommentService {

	public Comment createComment(Comment comment, Long postId);
	public Long deleteCommentById(Long commentId);
}
