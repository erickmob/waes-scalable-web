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
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Api(value = "v1") public interface PayloadDiffApi {
	@ApiOperation(value = "Comparison REST Endpoint", nickname = "getPayloadDifferences", response = PayloadDiffResult.class, tags = {
			"payloads"}) @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = PayloadDiffResult.class),
			@ApiResponse(code = 404, message = "Not found")}) @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE) ResponseEntity<PayloadDiffResult> getPayloadDifferences(
			@ApiParam(value = "id", required = true) @PathVariable("id") String id);
}
