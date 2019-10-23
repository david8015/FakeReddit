package com.ga.service;

import java.util.List;

import com.ga.entity.Comment;
import com.ga.entity.Post;

public interface PostService {
	public List<Post> listPosts();
	public Post createPost(Post post);
	public Long DeletePostById(Long postId);
	public Post updatePost(Post post, Long postId);
	public List<Comment> getCommentsByPostId(Long postId);

}
