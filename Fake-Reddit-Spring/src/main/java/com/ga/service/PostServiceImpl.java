package com.ga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ga.dao.PostDao;
import com.ga.entity.Post;

public class PostServiceImpl implements PostService{
	
	@Autowired
	PostDao postDao;

	@Override
	public List<Post> listPosts() {
		return postDao.listPosts();
	}
	
	@Override
	public Post createPost(Post post) {
		return postDao.createPost(post);
		
	}

}
