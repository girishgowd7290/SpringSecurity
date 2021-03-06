package com.ggbs.springsecurity.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ggbs.springsecurity.model.AuthorisationRequest;
import com.ggbs.springsecurity.model.AuthorisationResponse;
import com.ggbs.springsecurity.service.MyUserDetailsService;
import com.ggbs.springsecurity.utils.JwtUtil;

@RestController
public class SecurityController {

	// To validate the credentias provided to Authenticate()
	@Autowired
	private AuthenticationManager authenticationManager;
	
	/// To generate the JWT Token if credentials are validated
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	// UserDetailService to fetch UserDetails object from Input to genereate UserDetails object
	@Autowired
	private MyUserDetailsService userDetailsService;

	/**
	 * authenticate is to perform below operations
	 * 1. validate the username, password sent in input request with AM
	 * 2. Get the UserDetails objject from UserDetailsService providing username
	 * 3. Call JwtUtil to generate token for the userDetail object
	 * 4. return JWT Token 
	 * 
	 * @param authorisationRequest
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/authenticate")
	public ResponseEntity<AuthorisationResponse> authenticate(@RequestBody AuthorisationRequest authorisationRequest) throws Exception {

		try {
			// 1. Authenticate the provided credentials in input request
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							authorisationRequest.getUsername(),
							authorisationRequest.getPassword())
					);
		}
		// throw exception if the credentials doesn match
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}
		
		// 2. Fetch UserDetails object from UserDetailsService by passing Username
		UserDetails userDetails = userDetailsService.loadUserByUsername(authorisationRequest.getUsername());
		
		// 3. Call JwtUtil to genertate the JWT Token 
		final String jwtToken = jwtTokenUtil.generateToken(userDetails);

		// 4. Return JWT token in response
		return ResponseEntity.ok().body(new AuthorisationResponse(jwtToken));
	}
	
	

	@GetMapping("/security")
	public String securityWithJWT() {
		return "Hello Girish, we are trying Spring Security with JWT";
	}
}

