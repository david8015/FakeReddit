package com.ga.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.entity.User;

@Repository
public class CommentDaoImpl implements CommentDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Comment> listcomments() {
		List<Comment> allComments = null;

		Session session = sessionFactory.getCurrentSession();

		try {
			session.beginTransaction();

			allComments = session.createQuery("FROM Comment").getResultList();
		} finally {
			session.close();
		}
	
		return allComments;
	}

	@Override
	public Comment createComment(Comment comment, String username, Long postId) {
		Session session = sessionFactory.getCurrentSession();
		
		User userCommenting = null;
		Post postCommentedOn = null;

		try {
			session.beginTransaction();
			
			userCommenting = (User) session.createQuery("FROM User u where u.username = '" +
					username + "'").uniqueResult();

			postCommentedOn = session.get(Post.class, postId);
			
			comment.setUser(userCommenting);
			comment.setPost(postCommentedOn);
			
			postCommentedOn.getComments().add(comment);
			
			session.save(comment);

			session.getTransaction().commit();
		} finally {
			session.close();
		}

		return comment;
	}
}
