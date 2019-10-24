package com.ga.dao;

import com.ga.entity.Comment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ga.entity.User;
import com.ga.entity.UserRole;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	UserRoleDao userRoleDao;
	
	@Override
	public User signup(User user) {
//		String roleName = user.getUserRole().getName();
		
//		UserRole userRole = userRoleDao.getRole(roleName);
		
		Session session = sessionFactory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
//			user.setUserRole(userRole);
			
			session.save(user);
			
			session.getTransaction().commit();
		} finally {
			session.close();
		}
		
		return user;
}

	@Override
	public User getUserByEmail(String email) {
		User user = null;
		
		Session session = sessionFactory.getCurrentSession();
		
		try {
			session.beginTransaction();
			user = (User) session.createQuery("FROM User u where u.email = '" + 
			email + "'").uniqueResult();
		}finally {
			session.close();
		}
		
		return user;
	}

	@Override
	public User login(User user) {
		User savedUser = null;
		
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			
			savedUser = (User)session.createQuery("FROM User u WHERE u.email = '" + 
				user.getEmail() + "'").getSingleResult();
		} finally {
			session.close();
		}
		
		return savedUser;
	}

	@Override
	public List<Comment> getCommentByUser(User user) {
		return null;
	}
}
