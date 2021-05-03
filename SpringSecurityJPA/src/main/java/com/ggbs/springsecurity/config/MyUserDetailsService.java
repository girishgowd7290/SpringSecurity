package com.ggbs.springsecurity.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ggbs.springsecurity.jpa.UserRepository;
import com.ggbs.springsecurity.model.JPAUserDetails;
import com.ggbs.springsecurity.model.User;


@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		//return new MyUserDetails(username); // used for hardcoding purpose

		Optional<User> user =userRepository.findByUsername(username);
		user.orElseThrow(() -> new UsernameNotFoundException("User Not found ::" + username));
		return user.map(JPAUserDetails::new).get();
	}

}
