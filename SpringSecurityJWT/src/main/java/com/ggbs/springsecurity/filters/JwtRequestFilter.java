package com.ggbs.springsecurity.filters;


import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ggbs.springsecurity.service.MyUserDetailsService;
import com.ggbs.springsecurity.utils.JwtUtil;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{

	/// To fetch the username from the token
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@Autowired
	private MyUserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		final String authorisationHeader = request.getHeader("Authorization");

		String jwt = null;
		String username = null;

		if(null != authorisationHeader && authorisationHeader.startsWith("Bearer ")) {
			jwt = authorisationHeader.substring(7);
			username = jwtTokenUtil.extractUsername(jwt);
		}
		
		if(null != username && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			
			if(jwtTokenUtil.validateToken(jwt, userDetails)) {
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						  userDetails, null, userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}
		chain.doFilter(request, response);
	}


}
