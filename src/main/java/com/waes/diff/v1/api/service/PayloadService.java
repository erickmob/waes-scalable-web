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

import com.waes.diff.v1.api.domain.response.PayloadResponse;
import com.waes.diff.v1.api.repository.PayloadRepository;
import com.waes.diff.v1.api.repository.entity.Payload;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor public class PayloadService {

  private final PayloadRepository payloadRepository;

  public PayloadResponse save(Payload payload) {
    Payload saved = payloadRepository.save(payload);
    return PayloadResponse.create().status(HttpStatus.CREATED).message(String.format("Payload [id:%s] created", saved.getId()));
  }
}
