package com.ga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ga.Dao.UserDao;
import com.ga.entity.User;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao userDao;

	public User signup(User user) {
		return userDao.signup(user);
	}

}
