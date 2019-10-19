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

import static com.waes.diff.v1.api.domain.response.PayloadResponse.create;
import static com.waes.diff.v1.api.factory.PayloadFactory.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.waes.diff.v1.api.repository.entity.Payload;
import com.waes.diff.v1.api.service.PayloadService;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PayloadController.class)
class PayloadControllerTest {

  @Autowired MockMvc mockMvc;
  @MockBean PayloadService payloadService;

  @Test
  void addLeftPayload_whenValidData_AddToCollection() throws Exception {
    given(payloadService.save(any(Payload.class)))
        .willReturn(create().message(createdMessage(ID_1)));
    mockMvc
        .perform(
            post("/v1/diff/{id}/left", ID_1)
                .contentType(APPLICATION_JSON_VALUE)
                .content(payloadContent(leftPayload1())))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.message", Is.is(createdMessage(ID_1))))
        .andExpect(content().contentType(APPLICATION_JSON_VALUE));
    verify(payloadService).save(any(Payload.class));
  }

  @Test
  void addRightPayload_whenValidData_AddToCollection() throws Exception {
    given(payloadService.save(any(Payload.class)))
        .willReturn(create().message(createdMessage(ID_2)));
    mockMvc
        .perform(
            post("/v1/diff/{id}/right", ID_2)
                .contentType(APPLICATION_JSON_VALUE)
                .content(payloadContent(rightPayload1())))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.message", Is.is(createdMessage(ID_2))))
        .andExpect(content().contentType(APPLICATION_JSON_VALUE));
    verify(payloadService).save(any(Payload.class));
  }

  @Test
  void whenAddLeftPayloadWithInvalidBody_returnNotFound() throws Exception {
    mockMvc
        .perform(
            post("/v1/diff/{id}/left", ID_1)
                .content(INVALID_PAYLOAD_CONTENT)
                .contentType(APPLICATION_JSON_VALUE))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.content", Is.is("Content should not be null or empty")));
  }

  @Test
  void whenAddRightPayloadWithInvalidBody_returnNotFound() throws Exception {
    mockMvc
        .perform(
            post("/v1/diff/{id}/right", ID_2)
                .content(INVALID_PAYLOAD_CONTENT)
                .contentType(APPLICATION_JSON_VALUE))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.content", Is.is("Content should not be null or empty")));
  }
}
