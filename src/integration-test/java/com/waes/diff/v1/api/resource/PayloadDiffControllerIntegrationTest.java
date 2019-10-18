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

import com.waes.diff.v1.api.domain.response.PayloadResponse;
import com.waes.diff.v1.api.repository.entity.Payload;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;

import static com.waes.diff.v1.api.factory.PayloadFactory.ID_1;
import static com.waes.diff.v1.api.factory.PayloadFactory.leftPayload1;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class) @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) public class PayloadDiffControllerIntegrationTest {

	@Autowired private TestRestTemplate restTemplate;

	@Test public void getPayloadDifferences() {
		HashMap<String, String> params = new HashMap<>();
		params.put("id", ID_1);
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("/v1/diff/{id}}");
		HttpEntity<Payload> payloadEntity = new HttpEntity<>(leftPayload1());
		ResponseEntity<PayloadResponse> response = restTemplate
				.exchange(builder.buildAndExpand(params).toUri(), HttpMethod.GET, payloadEntity, PayloadResponse.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
}
