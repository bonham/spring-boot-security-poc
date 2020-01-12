package com.example.spring;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BasicAuthTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void greetingShouldReturnDefaultMessage() throws Exception {
	
		String baseUrl = "http://localhost:" + port;
		TestRestTemplate trt = this.restTemplate;
		
		//RestTemplate rt = trt.getRestTemplate();
		
		ResponseEntity<String> response1 = trt.withBasicAuth("admin", "admin")
				.getForEntity(baseUrl + "/inform", String.class);
		
		assertThat(response1.getStatusCode().equals(HttpStatus.OK));
		assertThat(response1.getBody().contains("informed"));
				
		ResponseEntity<String> secondValidResponse = trt
				.getForEntity(baseUrl + "/inform", String.class);
		assertThat(secondValidResponse.getStatusCode().equals(HttpStatus.OK));
		

		ResponseEntity<String> thirdInvalidResponse = trt
				.getForEntity(baseUrl + "/1", String.class);

		assertThat(thirdInvalidResponse.getStatusCode().is3xxRedirection());
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