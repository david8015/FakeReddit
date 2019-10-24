package com.ga.dao;
import com.ga.entity.Comment;
import com.ga.entity.User;

import java.util.List;

public interface UserDao {
	public User signup(User user);
	public User getUserByEmail(String email);
	public User login(User user);
	public List<Comment> getCommentByUser(User user);


	
}
