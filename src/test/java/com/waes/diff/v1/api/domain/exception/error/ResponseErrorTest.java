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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class ResponseErrorTest {

  @Test
  void create_responseErrorWithStatusAndMessage() {
    LocalDateTime dateTime = LocalDateTime.now();
    ResponseError error =
        ResponseError.create().message("Lorem ipsum dolor sit amet").status(HttpStatus.ACCEPTED);
    error.setTimestamp(dateTime);
    assertNotNull(error);
    assertThat(error.getMessage(), is(equalTo("Lorem ipsum dolor sit amet")));
    assertThat(error.getStatus(), is(equalTo(HttpStatus.ACCEPTED.value())));
    assertThat(error.getTimestamp(), is(equalTo(dateTime)));
  }
}
