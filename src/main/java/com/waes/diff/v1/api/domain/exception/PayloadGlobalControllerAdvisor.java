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
package com.waes.diff.v1.api.domain.exception;

import com.waes.diff.v1.api.domain.exception.error.ResponseError;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice @RestControllerAdvice public class PayloadGlobalControllerAdvisor {

	@ResponseStatus(HttpStatus.BAD_REQUEST) @ExceptionHandler(DuplicateKeyException.class) public ResponseError handleDuplicatedKey() {
		return ResponseError.create().status(HttpStatus.BAD_REQUEST)
				.message("The payload you are trying to add has already been added previously.");
	}

	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED) @ExceptionHandler(HttpClientErrorException.MethodNotAllowed.class) public ResponseError handleOperationNotAllowed() {
		return ResponseError.create().status(HttpStatus.METHOD_NOT_ALLOWED)
				.message("The payload you are trying to add has already been added previously.");
	}

	@ResponseStatus(HttpStatus.NOT_FOUND) public ResponseError handleNotFound(Exception ex) {
		return ResponseError.create().status(HttpStatus.NOT_FOUND).message(ex.getMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST) @ExceptionHandler(MethodArgumentNotValidException.class) public Map<String, String> handleValidationExceptions(
			MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

	@ExceptionHandler(ConstraintViolationException.class) public void constraintViolationException(HttpServletResponse response)
			throws IOException {
		response.sendError(HttpStatus.BAD_REQUEST.value());
	}
}
