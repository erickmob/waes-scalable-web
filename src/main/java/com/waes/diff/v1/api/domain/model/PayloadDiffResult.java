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
package com.waes.diff.v1.api.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.waes.diff.v1.api.domain.enums.Result;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Holds the comparison result - EQUAL - NOT_EQUAL - SIZE_NOT_MATCH
 */
@ApiModel(description = "Holds the comparison result   - EQUAL  - NOT_EQUAL  - SIZE_NOT_MATCH  ") @Validated @Data public class PayloadDiffResult {
	@JsonProperty("result") @ApiModelProperty("result") private Result result;

	@Valid @JsonProperty("details") @ApiModelProperty("details") private List<DiffDetails> details;

	private PayloadDiffResult() {
		details = Optional.ofNullable(details).orElse(new LinkedList<>());
	}

	public static PayloadDiffResult create() {
		return new PayloadDiffResult();
	}

	/**
	 * Payload's result data
	 *
	 * @param result the diff result
	 * @return current object instance
	 */
	public PayloadDiffResult result(Result result) {
		this.result = result;
		return this;
	}

	/**
	 * Adds a list of details
	 *
	 * @param details the payload diff result details
	 * @return current object instance
	 */
	public PayloadDiffResult addDetails(List<DiffDetails> details) {
		this.details.addAll(details);
		return this;
	}
}
