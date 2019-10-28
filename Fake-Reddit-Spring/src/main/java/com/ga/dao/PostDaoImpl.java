package com.ga.dao;

import java.util.List;

import com.ga.entity.Comment;
import com.ga.entity.User;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ga.entity.Post;

@Repository
public class PostDaoImpl implements PostDao{
	//	@Autowired
    //	UserDao userDao;

	//Injecting a session factory for database operations
	@Autowired
	private SessionFactory sessionFactory;

	//query the database and return a list of all posts
	@Override
	public List<Post> listPosts() {

		List<Post> allPosts = null;

		Session session = sessionFactory.getCurrentSession();

		try {
			session.beginTransaction();

			allPosts = session.createQuery("FROM Post").getResultList();
		} finally {
			session.close();
		}

		return allPosts;
	}

	//creates a post in database and links it to user by userId
	@Override
	public Post createPost(Post post, String username) {
		User user = null;
		Session session = sessionFactory.getCurrentSession();


		try {
			session.beginTransaction();
			user = (User) session.createQuery("FROM User u where u.username = '" +
					username + "'").uniqueResult();
			post.setUser(user);
			session.save(post);

			session.getTransaction().commit();
		} finally {
			session.close();
		}

		return post;
	}

	//deletes a post in database by postId
	@Override
	public Long deletePostByPostId(Long postId) {

		Session session = sessionFactory.getCurrentSession();
		
		Post postToDelete = null;
		
		try {
			session.beginTransaction();
			
			postToDelete = session.get(Post.class, postId);
			
			session.delete(postToDelete);
			
			session.getTransaction().commit();
		}finally {
			session.close();
		}
		return postToDelete.getId();
	}

	//updates a post in database
	@Override
	public Post updatePost(Post post, Long postId) {
		Post postToUpdate = null;
		Session session = sessionFactory.getCurrentSession();
		
		try {
			session.beginTransaction();
			postToUpdate = session.get(Post.class, postId);
			postToUpdate.setTitle(post.getTitle());
			postToUpdate.setDescription(post.getDescription());
			
			session.update(postToUpdate);
			
			session.getTransaction().commit();
			
		} finally {
			session.close();
		}
		return postToUpdate;
	}

	//query database and return a list of comments for the specified post (postId)
	@Override
	public List<Comment> getCommentsByPostId(Long postId) {
		List<Comment> commentList = null;
		Post post = null;

		Session session = sessionFactory.getCurrentSession();

		try{
			session.beginTransaction();
			post = session.get(Post.class, postId);
			commentList = post.getComments();

			Hibernate.initialize(commentList);
		}finally {
			session.close();
		}

		return commentList;
	}
}
