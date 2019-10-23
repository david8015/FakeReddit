package com.ga.service;

import java.util.ArrayList;
import java.util.List;

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

        if(user==null)
            throw new UsernameNotFoundException("Unknown user: " + email);

        return new org.springframework.security.core.userdetails.User(user.getEmail(), bCryptPasswordEncoder.encode(user.getPassword()),
                true, true, true, true, getGrantedAuthorities(user));
    }
    
    private List<GrantedAuthority> getGrantedAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        authorities.add(new SimpleGrantedAuthority(user.getUserRole().getName()));

        return authorities;
        }

    @Autowired
    JwtUtil jwtUtil;
    
     @Override
     public String login(User user) {
         User foundUser = userDao.login(user);
         if(foundUser != null &&
                 foundUser.getUserId() != null &&
                 bCryptPasswordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
             UserDetails userDetails = loadUserByUsername(foundUser.getEmail());
              
             return jwtUtil.generateToken(userDetails);
            }
         return null;
     }

}
