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
package com.waes.diff.v1.api.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waes.diff.v1.api.domain.enums.Direction;
import com.waes.diff.v1.api.repository.entity.Payload;
import org.assertj.core.util.Lists;

import java.util.List;

import static com.waes.diff.v1.api.repository.entity.Payload.create;

public class PayloadFactory {

  public static final String ID_1 = "id_1";
  public static final String ID_2 = "id_2";
  public static final String INVALID_PAYLOAD_CONTENT = "{\"content\" : \"\"}";
  public static final String RIGHT_1 = "IHsKICAgIm5hbWUiOiJXYWVzIiwKICAgImNvdW50cnkiOiJOZXRoZXJsYW5kcyIKIH0K";
  private static final String LEFT_1 = "IHsKICAgIm5hbWUiOiJXYWVzIiwKICAgImNvdW50cnkiOiJOZXRoZXJsYW5kcyIKIH0K";
  private static final String LEFT_2 = "IHsKICAgIm5hbWUiOiIxQnl0ZSIsCiAgICJjb3VudHJ5IjoiQnJhemlsIgogfQo=";
  private static final String RIGHT_2 = "IHsKICAgIm5hbWUiOiIxQnl0ZSIsCiAgICJjb3VudHJ5IjoiQnJhemlsIgogfQo=";
  private static final String LEFT_3 = "Y29tcGFyYXRpb24gc2l6ZQ==";
  private static final String RIGHT_3 = "a29tcGFyYXRpb24gc2l0ZQ==";

  private static final String CREATED_MESSAGE = "Payload [id:%s] created";

  public static Payload leftPayload1() {
    return Payload.create().content(LEFT_1).direction(Direction.LEFT).id(ID_1);
  }

  public static Payload rightPayload1() {
    return create().content(RIGHT_1).direction(Direction.RIGHT).id(ID_1);
  }

  public static Payload leftPayload2() {
    return create().content(LEFT_2).direction(Direction.LEFT).id(ID_2);
  }

  private static Payload rightPayload2() {
    return create().content(RIGHT_2).direction(Direction.RIGHT).id(ID_2);
  }

  private static Payload leftPayload3() {
    return create().content(LEFT_3).direction(Direction.LEFT).id(ID_2);
  }

  private static Payload rightPayload3() {
    return create().content(RIGHT_3).direction(Direction.RIGHT).id(ID_2);
  }

  public static List<Payload> listOfPayload() {
    return Lists.newArrayList(leftPayload1(), rightPayload1());
  }

  public static List<Payload> sameSizeNotEqualListOfPayload() {
    return Lists.newArrayList(leftPayload3(), rightPayload3());
  }

  public static List<Payload> notEqualListOfPayload() {
    return Lists.newArrayList(leftPayload1(), rightPayload2());
  }

  public static String payloadContent(Payload payload) throws JsonProcessingException {
    return new ObjectMapper().writeValueAsString(payload);
  }

  public static String createdMessage(String uid) {
    return String.format(CREATED_MESSAGE, uid);
  }
}
