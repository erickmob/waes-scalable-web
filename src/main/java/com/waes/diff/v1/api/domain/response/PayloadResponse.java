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
package com.waes.diff.v1.api.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;

/**
 * Payload Response
 */
@ApiModel(description = "Payload Response") @Validated @Data @NoArgsConstructor(access = AccessLevel.PRIVATE) public class PayloadResponse {

	@JsonProperty("message") @ApiModelProperty("message") private String message;

	@JsonProperty("status") @ApiModelProperty("status") private HttpStatus status;

	public static PayloadResponse create() {
		return new PayloadResponse();
	}

	/**
	 * Adds a description message
	 *
	 * @param message the response message
	 * @return The payload response
	 */
	public PayloadResponse message(final String message) {
		this.message = message;
		return this;
	}

	/**
	 * Adds a http status
	 *
	 * @param status Http Status
	 * @return The payload response
	 */
	public PayloadResponse status(final HttpStatus status) {
		this.status = status;
		return this;
	}
}
