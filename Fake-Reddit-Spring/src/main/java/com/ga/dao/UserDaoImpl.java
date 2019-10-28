package com.ga.dao;

import com.ga.entity.Comment;
import com.ga.entity.Post;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ga.entity.User;
//import com.ga.entity.UserRole;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

	//Injecting a session factory for database operations
	@Autowired
	SessionFactory sessionFactory;

	//Adds new user to database
	//assigns every user a role of USER automatically
	@Override
	public User signup(User user) {
		
		Session session = sessionFactory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			user.setRole("USER");
			
			session.save(user);
			
			session.getTransaction().commit();
		} finally {
			session.close();
		}
		
		return user;
}
	//Fetches a user from the database by email
	//Used in service layer
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

	//returns a user from a database
	//takes in email and password from controller
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

	//Returns a list of comments by userId
	//loads user then uses the getComments() method on that user
	//Hibernate.initialize() circumvents lazy loading
	@Override
	public List<Comment> getCommentByUser(Long userId ) {
		List<Comment> commentList = null;
		User user2;

		Session session = sessionFactory.getCurrentSession();

		try{
			session.beginTransaction();
			user2 = session.get(User.class, userId);
			commentList = user2.getComments();
			Hibernate.initialize(commentList);

		} finally {
			session.close();
		}
		return commentList;
	}
	//Returns a list of posts by userId
	//loads user then uses the getPosts() method on that user
	//Hibernate.initialize() circumvents lazy loading
	@Override
	public List<Post> gePostsByUser(Long userId) {
		List<Post> postList= null;
		User user2;

		Session session = sessionFactory.getCurrentSession();

		try{
			session.beginTransaction();
			user2 = session.get(User.class, userId);
			postList = user2.getPosts();
			Hibernate.initialize(postList);

		} finally {
			session.close();
		}
		return postList;
	}
}
