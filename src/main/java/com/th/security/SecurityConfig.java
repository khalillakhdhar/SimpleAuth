package com.th.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	//pour la config de la mode d'authentification
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws
	Exception {
		
		auth.inMemoryAuthentication().withUser("admin").password("{noop}1234").roles("USER","ADMIN");		
		auth.inMemoryAuthentication().withUser("user").password("{noop}1234").roles("USER");
	}
	// Pour les autorisations
	@Override
	protected void configure(HttpSecurity http) throws Exception { 
		http.formLogin();
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/login/**","register/**").permitAll(); // les url sont autorisé pour tout le monde 
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/tasks/**").hasRole("ADMIN"); // POST à l'url tasks/**  peut être effectuer uniquement par l'admin
		http.authorizeRequests().anyRequest().authenticated(); // autoriser l'accés des autres routage si l'utilisateur est authentifié
		
		
	}
	
}