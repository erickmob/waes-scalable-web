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
package com.waes.diff.v1.api.service;

import static com.waes.diff.v1.api.factory.PayloadFactory.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.waes.diff.v1.api.domain.enums.Direction;
import com.waes.diff.v1.api.domain.response.PayloadResponse;
import com.waes.diff.v1.api.repository.PayloadRepository;
import com.waes.diff.v1.api.repository.entity.Payload;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.http.HttpStatus;

@DataMongoTest
public class PayloadServiceTest {

  @Autowired private PayloadRepository payloadRepository;
  private PayloadService payloadService;

  @BeforeEach
  public void setUp() {
    payloadService = new PayloadService(payloadRepository);
  }

  @Test
  public void save() {
    PayloadResponse payloadResponse =
        payloadService.save(Payload.create().id(ID_1).direction(Direction.RIGHT).content(RIGHT_1));
    assertEquals(createdMessage(ID_1), payloadResponse.getMessage());
    assertEquals(HttpStatus.CREATED, payloadResponse.getStatus());
  }
}
