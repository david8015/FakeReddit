package com.ga.Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ga.entity.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	SessionFactory sessionFactory;

	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	public User signup(User user) {
		Session session = sessionFactory.getCurrentSession();
		
		try {
			session.beginTransaction();
			session.save(user);
			
			session.getTransaction().commit();
		}finally {
			session.close();
		}
		
		return user;
	}
}
