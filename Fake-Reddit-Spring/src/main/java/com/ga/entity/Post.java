package com.ga.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="post")
public class Post {

	private Long postId;
	private String userName;
	private String description;
	
	public Post() {
	}

	public Post(Long postId, String userName, String description) {
		super();
		this.postId = postId;
		this.userName = userName;
		this.description = description;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
