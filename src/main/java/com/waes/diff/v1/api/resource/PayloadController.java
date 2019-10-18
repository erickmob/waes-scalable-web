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

import static com.waes.diff.v1.api.repository.entity.Payload.create;
import static org.springframework.http.ResponseEntity.status;

import com.waes.diff.v1.api.domain.enums.Direction;
import com.waes.diff.v1.api.domain.request.PayloadRequestBody;
import com.waes.diff.v1.api.domain.response.PayloadResponse;
import com.waes.diff.v1.api.service.PayloadService;
import io.swagger.annotations.Api;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/v1/diff")
@RequiredArgsConstructor
@Api(value = "v1", tags = "payloads")
public class PayloadController implements PayloadApi {

  private final PayloadService payloadService;

  /**
   * Creates a {@code Payload} register on the database to be compared later.
   *
   * @param id Path parameter
   * @param body JSON Object from body request
   * @param direction The side to used in the comparison
   * @return {@code PayloadResponse} with a message and a status code.
   */
  private PayloadResponse save(String id, PayloadRequestBody body, Direction direction) {
    return payloadService.save(create().id(id).content(body.getContent()).direction(direction));
  }

  /**
   * Adds a new Left side {@code Payload} data
   *
   * @param id payload
   * @param requestBody with a encoded JSON
   * @return {@code PayloadResponse}
   */
  @Override
  public ResponseEntity<PayloadResponse> addLeftPayload(
      String id, @Valid PayloadRequestBody requestBody) {
    return status(HttpStatus.CREATED).body(save(id, requestBody, Direction.LEFT));
  }

  /**
   * Adds a new Right side {@code Payload} data
   *
   * @param id payload
   * @param requestBody with a encoded JSON
   * @return {@code PayloadResponse}
   */
  @Override
  public ResponseEntity<PayloadResponse> addRightPayload(
      String id, @Valid PayloadRequestBody requestBody) {
    return status(HttpStatus.CREATED).body(save(id, requestBody, Direction.RIGHT));
  }
}
