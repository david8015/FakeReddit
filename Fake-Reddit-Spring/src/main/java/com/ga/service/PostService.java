package com.ga.service;

import java.util.List;

import com.ga.entity.Post;

public interface PostService {
	public List<Post> listPosts();
	public Post createPost(Post post);

}
