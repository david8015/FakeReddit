package com.ga.dao;

import java.util.List;

import com.ga.entity.Comment;



public interface CommentDao {
	public List<Comment> listcomments();
	public Comment createComment(Comment comment);

}
