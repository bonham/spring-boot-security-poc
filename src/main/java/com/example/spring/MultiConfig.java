package com.example.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


public class MultiConfig {

	@Configuration
	@Order(1)
	public static class PermitWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
	
		@Override
		protected void configure(HttpSecurity http)  throws Exception {
	
			http
				.requestMatchers()
					.antMatchers("/")
					.antMatchers("/login")
					.and()
				.authorizeRequests()
					.anyRequest()
					.permitAll()
				.and().formLogin(); // this is necessary that form login page will be generated !!
		}
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			
			auth
				.inMemoryAuthentication()
					.withUser("micha")
					.password("{noop}supp")
					.roles("USER");
		}
	}

	
	@Configuration
	@Order(2)
	public static class FormWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
	
		@Override
		protected void configure(HttpSecurity http)  throws Exception {
	
			http
				.requestMatchers()
					.antMatchers("/1")
//					.antMatchers("/2")
					.and()
				.authorizeRequests()
					.anyRequest().hasRole("USER")
					.and()
				.formLogin();
		}

		

	}
	
	@Configuration
	@Order(3)
	public static class BasicSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
			
		@Override
		protected void configure(HttpSecurity http)  throws Exception {
	
		http
			.authorizeRequests( authorizeRequests ->
				authorizeRequests
					.antMatchers("/inform").hasRole("ADMIN")
					.anyRequest().denyAll()
			)
			.httpBasic();
		}
		
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			
			auth
				.inMemoryAuthentication()
					.withUser("admin")
					.password("{noop}admin")
					.roles("ADMIN");
					
		}
	}
	
	
	
}
