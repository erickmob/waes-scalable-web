/*
 * Copyright 2015-2019 John Silva.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 
package com.waes.diff.v1.api.resource;

import com.waes.diff.v1.api.repository.entity.Payload;
import com.waes.diff.v1.api.service.PayloadService;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static com.waes.diff.v1.api.domain.response.PayloadResponse.create;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PayloadController.class) class PayloadControllerTest {

  final String leftPayload_1 = "{\"content\":\"Y29tcGFyYXRpb24gc2l6ZQ==\"}";
  final String rightPayload_1 = "{\"content\":\"Y29tcGFyYXRpb24gc2l6ZQ==\"}";
  final String leftPayload_2 = "{\"content\":\"a29tcGFyYXRpb24gc2l0ZQ==\"}";
  final String rightPayload_2 = "{\"content\":\"a29tcGFyYXRpb24gc2l0ZQ==\"}";
  final String invalidPayload = "{\"content\":\"\"}";

  @Autowired MockMvc mockMvc;
  @MockBean PayloadService payloadService;

  @BeforeEach void setUp() {
  }

  @Test void addLeftPayload_whenValidData_AddToCollection() throws Exception {
    given(payloadService.save(any(Payload.class))).willReturn(create().message("CREATED"));
    mockMvc.perform(post("/v1/diff/{id}/left", "uid").contentType(APPLICATION_JSON).content(leftPayload_1))
            .andExpect(status().isCreated()).andExpect(jsonPath("$.message", Is.is("CREATED")))
            .andExpect(content().contentType(APPLICATION_JSON));
    verify(payloadService).save(any(Payload.class));
  }

  @Test void addRightPayload_whenValidData_AddToCollection() throws Exception {
    given(payloadService.save(any(Payload.class))).willReturn(create().message("CREATED"));
    mockMvc.perform(post("/v1/diff/{id}/right", "uid").content(rightPayload_1).contentType(APPLICATION_JSON))
            .andExpect(status().isCreated()).andExpect(jsonPath("$.message", Is.is("CREATED")))
            .andExpect(content().contentType(APPLICATION_JSON));
    verify(payloadService).save(any(Payload.class));
  }

  @Test void whenAddLeftPayloadWithInvalidBody_returnNotFound() throws Exception {
    mockMvc.perform(post("/v1/diff/{id}/left", "udi").content(invalidPayload).contentType(APPLICATION_JSON))
            .andExpect(status().isBadRequest()).andExpect(jsonPath("$.content", Is.is("Content should not be null or empty")));
  }

  @Test void whenAddRightPayloadWithInvalidBody_returnNotFound() throws Exception {
    mockMvc.perform(post("/v1/diff/{id}/right", "udi").content(invalidPayload).contentType(APPLICATION_JSON))
            .andExpect(status().isBadRequest()).andExpect(jsonPath("$.content", Is.is("Content should not be null or empty")));
  }
}
