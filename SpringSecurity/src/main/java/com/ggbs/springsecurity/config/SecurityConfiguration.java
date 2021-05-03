package com.ggbs.springsecurity.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	DataSource datasource;

	/**
	 * Authentication types with different ways
	 * 
	 * 1. Using InMemeryAuthentication
	 * 2. Using JDBC Authentication with DefaultSchema and Custom users
	 * 3. Using JDBC Authentication with Default SQL and Custom users
	 * 4. Using JDBC Authentication with Custome SQL queries and Custome users
	 */

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// To make use of JDBC Authentication with named queries
		auth.jdbcAuthentication()
		.dataSource(datasource)
		.usersByUsernameQuery("select username,password,enabled from users where username = ?")
		.authoritiesByUsernameQuery("Select username,authority from authorities where username = ?");
	}

	/*
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// To make use of JDBC Authentication with our own schema.sql and data.sql
		auth.jdbcAuthentication()
		.dataSource(datasource);

	}*/

	/*
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// To make use of JDBC Authentication  with default schema
		auth.jdbcAuthentication()
		.dataSource(datasource)
		.withDefaultSchema()
		.withUser(User.withUsername("Girish").password("Gowda").roles("Admin"))
		.withUser(User.withUsername("Sumana").password("Gumma").roles("User"));
	}*/

	/*
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Here we are using iIn Memory Authentication to authenticate logged in users
		//  Can add n number of users with roles here 
		auth.inMemoryAuthentication()
		.withUser("Girish")
		.password("Gowda")
		.roles("Admin")
		.and()
		.withUser("Sumana")
		.password("Gumma")
		.roles("User")
		.and()
		.withUser(User.withUsername("Sush").password("HB").roles("Admin"));

	}*/

	/**
	 * Used for encoding password
	 * @return
	 */
	@Bean
	public PasswordEncoder getPassword() {
		//NoOpPasswordEncoder does nt encode anything
		return NoOpPasswordEncoder.getInstance();
	}

	/**
	 * Authorisation using HTTP Security
	 * 1. Give Least access first n full access last
	 * 2. Use antPattern mathcer for different access to servlets
	 * 3. Use hasRole or hasAnyRole for 
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		// Least access should be placed first
		.antMatchers("/admin").hasRole("ADMIN")
		// Gives access to both Admin and User
		.antMatchers("/user").hasAnyRole("ADMIN", "USER")
		// Gives full access irrespecitve of role
		.antMatchers("/").permitAll()
		.and().formLogin();
	}

}
