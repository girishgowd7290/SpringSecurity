package com.ggbs.springsecurity.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
public class JPAUserDetails implements UserDetails {


	private String username;
	private String password;
	private boolean active;
	private List<GrantedAuthority> authority;


	public JPAUserDetails(User user) {
		this.username=user.getUsername();
		this.password=user.getPassword();
		this.active=user.isActive();
		this.authority = Arrays.stream(user.getRoles().split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());

	}

	public JPAUserDetails() {

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authority;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return active;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}



}
