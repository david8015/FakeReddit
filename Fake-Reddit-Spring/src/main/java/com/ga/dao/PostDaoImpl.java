package com.ga.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ga.entity.Post;

@Repository
public class PostDaoImpl implements PostDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Post> listPosts() {
		
		List <Post> allPosts = null;
		
		Session session = sessionFactory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			allPosts = session.createQuery("FROM Post").getResultList();
		}finally {
			session.close();
		}
		// TODO Auto-generated method stub
		return allPosts;
	}

	@Override
	public Post createPost(Post post) {
		Session session = sessionFactory.getCurrentSession();
		
		
		try {
			session.beginTransaction();
			
			
			session.save(post);
			
			session.getTransaction().commit();
		} finally {
			session.close();
		}
		
		return post;
}

	
}
