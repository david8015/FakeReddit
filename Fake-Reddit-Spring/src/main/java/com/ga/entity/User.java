package com.ga.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="users")
public class User {

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column //(columnDefinition = "String default USER")
	private String role = "USER";

	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	public User() {
	}


//	public User(Long userId, String email, String password) {
//		super();
//		this.userId = userId;
//		this.email = email;
//		this.password = password;
//	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "profile_id")
	private Profile profile;

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Post> posts;

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Comment> comments;

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
}

//	@ManyToOne(cascade = {CascadeType.DETACH,
//            CascadeType.MERGE, CascadeType.REFRESH})
//	@JoinColumn(name = "user_role_id")
//	private UserRole userRole;
//
//	public UserRole getUserRole() { return userRole; }
//
//	public void setUserRole(UserRole userRole) { this.userRole = userRole; }
//
//}
