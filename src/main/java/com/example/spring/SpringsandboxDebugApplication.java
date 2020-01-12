package com.example.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
@Import(MultiConfig.class)
public class SpringsandboxDebugApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringsandboxDebugApplication.class, args);
	}

}
