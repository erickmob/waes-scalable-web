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
package com.waes.diff.v1.api.domain.exception.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data @NoArgsConstructor(access = AccessLevel.PRIVATE) public class ResponseError {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss") private LocalDateTime timestamp = LocalDateTime
			.now();

	private Integer status;
	private String message;

	public static ResponseError create() {
		return new ResponseError();
	}

	public ResponseError message(String message) {
		this.message = message;
		return this;
	}

	public ResponseError status(HttpStatus status) {
		this.status = status.value();
		return this;
	}
}
