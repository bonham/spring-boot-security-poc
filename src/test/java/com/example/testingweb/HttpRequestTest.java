package com.example.testingweb;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import com.example.spring.SpringsandboxDebugApplication;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = SpringsandboxDebugApplication.class)
public class HttpRequestTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void greetingShouldReturnDefaultMessage() throws Exception {
		
		assertThat(
				this.restTemplate
				.withBasicAuth("admin", "admin")
				.getForObject("http://localhost:" + port + "/inform",
				String.class)).contains("informed");
	}
	
	@Test
	public void basicAuthShouldNotWorkOnFormEndpoints() throws Exception {
		
		assertThat(
				this.restTemplate
				.withBasicAuth("admin", "admin")
				.getForEntity("http://localhost:" + port + "/1",
				String.class).getStatusCode().is3xxRedirection());
	}
}