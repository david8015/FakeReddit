package com.ga.service;

import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.entity.Response;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.ga.entity.User;

import java.util.List;


public interface UserService extends UserDetailsService{
	public String signup(User user);

	//UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
	
	public Response login (User user);

	public String returnUsername();

	public List<Comment> getCommentsByUser(Long userId );

	public List<Post> getPostsByUser(Long userId );
}
