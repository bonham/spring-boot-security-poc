package com.example.test.websecurity;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.example.spring.SpringsandboxDebugApplication;

@SpringBootTest(classes = SpringsandboxDebugApplication.class)
@AutoConfigureMockMvc
public class WebSecurityTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void accessUnsecuredResourceThenOk() throws Exception {
		mockMvc.perform(get("/"))
			.andExpect(status().isOk());
		mockMvc.perform(get("/login"))
		.andExpect(status().isOk());
	}
	
	@Test
	public void accessFormSecuredResourceUnauthenticated() throws Exception {
		mockMvc.perform(get("/1"))
			.andExpect(status().is3xxRedirection());
	}

	@Test
	public void accessForbiddenResource() throws Exception {
		mockMvc.perform(get("/2"))
			.andExpect(status().isForbidden());
	}

	@Test
	public void accessBasicSecuredResourceUnauthenticated() throws Exception {
		mockMvc.perform(get("/inform"))
			.andExpect(status().isUnauthorized());
		
	}
	
	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	public void accessAfterBasicAuthSuccess() throws Exception {
		mockMvc.perform(get("/inform"))
			.andExpect(status().isOk());
		
	}

	@Test
	public void accessAfterBasicAuthSuccessNoMockUser() throws Exception {
		//String basicDigestHeader = "";
		mockMvc.perform(get("/inform"))
			.andExpect(status().isOk());
		
	}
}
