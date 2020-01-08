package com.example.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;


@EnableWebSecurity
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
	}

	
	@Configuration
	@Order(2)
	public static class FormWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
	
		@Override
		protected void configure(HttpSecurity http)  throws Exception {
	
			http
				.requestMatchers()
					.antMatchers("/1")
					.antMatchers("/2")
					.and()
				.authorizeRequests()
					.anyRequest().authenticated()
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
				.authorizeRequests()
						.anyRequest().authenticated()
						.and()
				.httpBasic();
		}		
	}
}
