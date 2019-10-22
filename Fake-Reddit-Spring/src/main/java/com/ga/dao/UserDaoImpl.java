package com.ga.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ga.entity.User;
import com.ga.entity.UserRole;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	UserRoleDao userRoleDao;
	
	@Override
	public User signup(User user) {
		String roleName = user.getUserRole().getName();
		
		UserRole userRole = userRoleDao.getRole(roleName);
		
		Session session = sessionFactory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			user.setUserRole(userRole);
			
			session.save(user);
			
			session.getTransaction().commit();
		} finally {
			session.close();
		}
		
		return user;
}

	@Override
	public User getUserByUsername(String username) {
		User user = null;
		
		Session session = sessionFactory.getCurrentSession();
		
		try {
			session.beginTransaction();
			user = (User) session.createQuery("FROM User u where u.username = '" + 
			username + "'").uniqueResult();
		}finally {
			session.close();
		}
		
		return user;
	}
}
