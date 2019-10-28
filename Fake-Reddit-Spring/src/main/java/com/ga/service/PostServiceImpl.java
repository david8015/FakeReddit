package com.ga.service;

import java.util.List;

import com.ga.entity.Comment;
import com.ga.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ga.dao.PostDao;
import com.ga.entity.Post;

@Service
public class PostServiceImpl implements PostService{
	//Injects postDao
	@Autowired
	private PostDao postDao;

	//Injects userService;
	@Autowired
	private UserService userService;


	//calls the postDao listPosts method to return a list of posts to the control layer
	@Override
	public List<Post> listPosts() {
		return postDao.listPosts();
	}

	//gets a user from userService layer by calling returnUsername()
	//sends a post and a username to dao layer to create and return a post to the control layer
	@Override
	public Post createPost(Post post) {
		String username = userService.returnUsername();
		return postDao.createPost(post, username);
		
	}

	//passes a postId to the Dao layer to delete a post - returns the postId deleted to the control layer
	@Override
	public Long DeletePostById(Long postId) {
		return postDao.deletePostByPostId(postId);
	}

	//passes a post and a postId to the Dao layer to update the post - returns the post to the control layer
	@Override
	public Post updatePost(Post post, Long postId) {
		return postDao.updatePost(post, postId);
	}

	//passes a postId to the Dao layer to get a list of comments for that post - returns the list to the control layer
	@Override
	public List<Comment> getCommentsByPostId(Long postId) {
		return postDao.getCommentsByPostId(postId);
	}

}
