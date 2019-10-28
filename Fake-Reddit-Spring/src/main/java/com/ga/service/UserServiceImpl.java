package com.ga.service;

import java.util.ArrayList;
import java.util.List;

import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ga.config.JwtUtil;
import com.ga.dao.UserDao;
import com.ga.entity.User;

@Service
public class UserServiceImpl implements UserService {

    //Injects userDao
	@Autowired
	UserDao userDao;

	//Injects PasswordEncoder
    //@Qualifier tells the program we are using the encoder interface
    @Autowired
    @Qualifier("encoder")
    PasswordEncoder bCryptPasswordEncoder;

	//used to return username from user currently logged in
    private String username;

    //sets encryptedPassword for that user
    //calls loadUserByUsername by sending it that user's email
    //returns a jwtToken which contains that user's username
	public String signup(User user) {
	    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

		if(userDao.signup(user).getUserId() != null) {
			UserDetails userDetails = loadUserByUsername(user.getEmail());
			
			return jwtUtil.generateToken(userDetails);
		}
		return null;
	}
	

    //takes in the user's email since they log in with it
    //calls userDao.getUserByEmail
    //sets that user's username into username
    //sends that username to userdetails for token encoding and encodes password
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDao.getUserByEmail(email);

        username = user.getUsername();

        if(user==null)
            throw new UsernameNotFoundException("Unknown user: " + email);

        return new org.springframework.security.core.userdetails.User(user.getUsername(), bCryptPasswordEncoder.encode(user.getPassword()),
                true, true, true, true, getGrantedAuthorities(user));
    }
    //Used for Spring security
    //authorizes users with a role to be able to perform certain operations
    //currently all "USERS" are granted authority
    private List<GrantedAuthority> getGrantedAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        authorities.add(new SimpleGrantedAuthority(user.getRole()));

        return authorities;
        }

    //Injects Jwutil
    @Autowired
    JwtUtil jwtUtil;

	//Initializes a Response object
	//Finds user by calling userDao.login()
    //checks if user exists, passwords match, and email matches
    //if it does, set the token and username attributes in the response object and return that object
     @Override
     public Response login(User user) {

         Response response = new Response();
         User foundUser = userDao.login(user);
         if(foundUser != null &&
                 foundUser.getUserId() != null &&
                 bCryptPasswordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
             UserDetails userDetails = loadUserByUsername(foundUser.getEmail());
             response.setToken(jwtUtil.generateToken(userDetails));
             response.setUsername(foundUser.getUsername());

             return response;
            }
         return null;
     }

     //used to return the username of a user
     public String returnUsername(){
         return username;
     }
    //calls userDao.getCommentsByUSer by sending it the userId
    @Override
    public List<Comment> getCommentsByUser(Long userId ) {
        return userDao.getCommentByUser(userId);
    }

    //calls userDao.getPostsByUser by sending it the userId
    @Override
    public List<Post> getPostsByUser(Long userId) {
        return userDao.gePostsByUser(userId);
    }

}
