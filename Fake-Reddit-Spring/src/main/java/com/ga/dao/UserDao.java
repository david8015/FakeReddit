package com.ga.dao;
import com.ga.entity.User;

public interface UserDao {
	public User signup(User user);
	public User getUserByEmail(String email);
	public User login(User user);
	
}
