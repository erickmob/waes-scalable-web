package com.waes.diff.v1.api.resource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest class PayloadControllerTest {

	final String leftPayload_1 = "{\"content\":\"Y29tcGFyYXRpb24gc2l6ZQ==\"}";
	final String rightPayload_1 = "{\"content\":\"Y29tcGFyYXRpb24gc2l6ZQ==\"}";
	final String leftPayload_2 = "{\"content\":\"a29tcGFyYXRpb24gc2l0ZQ==\"}";
	final String rightPayload_2 = "{\"content\":\"a29tcGFyYXRpb24gc2l0ZQ==\"}";
	@Autowired MockMvc mockMvc;

	@BeforeEach void setUp() {
	}

	@Test void addLeftPayload_whenValidData_AddToCollection() throws Exception {
		mockMvc.perform(post("/v1/diff/{id}/left", "uid").contentType(APPLICATION_JSON).content(leftPayload_1))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.message").isNotEmpty())
				.andExpect(content().contentType(APPLICATION_JSON));
	}
}
