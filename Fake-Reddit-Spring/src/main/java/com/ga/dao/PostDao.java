package com.ga.dao;

import java.util.List;

import com.ga.entity.Post;

public interface PostDao {
	public List<Post> listPosts();
	public Post createPost(Post post, String username);
	public Long DeletePostByPostId(Long postId);
	public Post updatePost(Post post, Long postId);
}
