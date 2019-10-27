package com.ga.dao;

import java.util.List;

import com.ga.entity.Comment;
import com.ga.entity.Post;

public interface PostDao {
	public List<Post> listPosts();
	public Post createPost(Post post, String username);
	public Long deletePostByPostId(Long postId);
	public Post updatePost(Post post, Long postId);
	public List<Comment> getCommentsByPostId(Long postId);
}
