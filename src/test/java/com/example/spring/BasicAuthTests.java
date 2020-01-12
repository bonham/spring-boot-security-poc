package com.example.spring;

import org.junit.jupiter.api.Test;

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

	@Test
	public void basicProtectedEndpointTest() throws Exception {
	
		String baseUrl = "http://localhost:" + port;
		//TestRestTemplate trt = this.restTemplate;

		TestRestTemplate trt = new TestRestTemplate(TestRestTemplate.HttpClientOption.ENABLE_COOKIES);

		ResponseEntity<String> response1 = trt.withBasicAuth("admin", "admin")
				.getForEntity(baseUrl + "/inform", String.class);
		
		assertThat(response1.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response1.getBody()).contains("informed");
				
		//Without user + password 
//		ResponseEntity<String> secondValidResponse = trt
//				.getForEntity(baseUrl + "/inform", String.class);
//		assertThat(secondValidResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
//		
//
//		ResponseEntity<String> thirdInvalidResponse = trt
//				.getForEntity(baseUrl + "/1", String.class);
//
//		assertThat(thirdInvalidResponse.getStatusCode()).isEqualTo(HttpStatus.FOUND);
	}
	
	@Test
	public void basicAuthShouldNotWorkOnFormEndpoints1() throws Exception {

		TestRestTemplate trt = new TestRestTemplate();

		assertThat(
				trt
				.withBasicAuth("admin", "admin")
				.getForEntity("http://localhost:" + port + "/1",
				String.class).getStatusCode()).isEqualTo(HttpStatus.FOUND);
	}
	
}