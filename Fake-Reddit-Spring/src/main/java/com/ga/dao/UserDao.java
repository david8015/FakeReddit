package com.ga.dao;
import com.ga.entity.User;

public interface UserDao {
	public User getUserByUsername(String username);
	public User signup(User user);
}
