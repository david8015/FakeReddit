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
	
	@Autowired
	private PostDao postDao;

	@Autowired
	private UserService userService;

	@Override
	public List<Post> listPosts() {
		return postDao.listPosts();
	}
	
	@Override
	public Post createPost(Post post) {
		String username = userService.returnUsername();
		return postDao.createPost(post, username);
		
	}

	@Override
	public Long DeletePostById(Long postId) {
		return postDao.DeletePostByPostId(postId);
		
	}

	@Override
	public Post updatePost(Post post, Long postId) {
		return postDao.updatePost(post, postId);
	}

	@Override
	public List<Comment> getCommentsByPostId(Long postId) {
		return postDao.getCommentsByPostId(postId);
	}

}
