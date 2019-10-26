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

	@Autowired
	UserDao userDao;

    private String username;

	public String signup(User user) {
	    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

		if(userDao.signup(user).getUserId() != null) {
			UserDetails userDetails = loadUserByUsername(user.getEmail());
			
			return jwtUtil.generateToken(userDetails);
		
		}
		
		return null;
	}
	
    @Autowired
    @Qualifier("encoder")
    PasswordEncoder bCryptPasswordEncoder;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDao.getUserByEmail(email);

        username = user.getUsername();

        if(user==null)
            throw new UsernameNotFoundException("Unknown user: " + email);

        return new org.springframework.security.core.userdetails.User(user.getUsername(), bCryptPasswordEncoder.encode(user.getPassword()),
                true, true, true, true, getGrantedAuthorities(user));
    }
    
    private List<GrantedAuthority> getGrantedAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        authorities.add(new SimpleGrantedAuthority(user.getRole()));

        return authorities;
        }

    @Autowired
    JwtUtil jwtUtil;
    
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
             //return jwtUtil.generateToken(userDetails);
            }
         return null;
     }

     public String returnUsername(){
         return username;
     }

    @Override
    public List<Comment> getCommentsByUser(Long userId ) {
        return userDao.getCommentByUser(userId);
    }

    @Override
    public List<Post> getPostsByUser(Long userId) {
        return userDao.gePostsByUser(userId);
    }

}
