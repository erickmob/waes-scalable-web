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

import com.waes.diff.v1.api.domain.model.PayloadDiffResult;
import com.waes.diff.v1.api.service.PayloadDiffService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated @RequiredArgsConstructor @RestController @RequestMapping("/v1/diff") @Api(value = "v1", tags = "payloads") public class PayloadDiffController
		implements PayloadDiffApi {

	private final PayloadDiffService payloadDiffService;

	@Override public ResponseEntity<PayloadDiffResult> getPayloadDifferences(String id) {
		return ResponseEntity.ok(payloadDiffService.getDiff(id));
	}
}
