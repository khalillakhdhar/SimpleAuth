package com.th.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	//pour la config de la mode d'authentification
	@Autowired
	private DataSource dataSource;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	  @Bean
	    public AuthenticationManager customAuthenticationManager() throws Exception {
	        return authenticationManager();
	    }

	  
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws
	Exception {
		
//		auth.inMemoryAuthentication().withUser("admin").password("{noop}1234").roles("USER","ADMIN");		
//		auth.inMemoryAuthentication().withUser("user").password("{noop}1234").roles("USER");
		
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery(
						"select username as principal ,password, active as credentials from app_user where username=?")
				.authoritiesByUsernameQuery(
						"select app_user_username as principal , roles_role_name as role from app_user_roles "
								+ "where app_user_username=?")
				.passwordEncoder(bCryptPasswordEncoder).rolePrefix("ROLE_");
		// formatage pour la lisibilité 

	}
	// Pour les autorisations
	@Override
	protected void configure(HttpSecurity http) throws Exception { 
		http.formLogin();
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/login/**","register/**","/").permitAll(); // les url sont autorisé pour tout le monde (sans authentification)
		
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/tasks/**").hasRole("ADMIN"); // POST à l'url tasks/**  peut être effectuer uniquement par l'admin
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/tasks/{id}").hasRole("ADMIN");
		http.authorizeRequests().anyRequest().authenticated(); // autoriser l'accés des autres routage si l'utilisateur est authentifié
		
		
	}
	
}