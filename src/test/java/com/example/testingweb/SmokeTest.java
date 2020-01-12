package com.example.testingweb;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.spring.Example;
import com.example.spring.SpringsandboxDebugApplication;

@SpringBootTest(classes= SpringsandboxDebugApplication.class)
public class SmokeTest {

	@Autowired
	private Example controller;

	@Test
	public void contexLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
}