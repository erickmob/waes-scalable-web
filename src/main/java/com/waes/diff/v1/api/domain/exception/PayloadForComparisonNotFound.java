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

import java.text.MessageFormat;

public class PayloadForComparisonNotFound extends RuntimeException implements PayloadResponseError {

  private static final long serialVersionUID = 1L;

  public PayloadForComparisonNotFound(String id) {
    super(MessageFormat.format("Requested Id [{0}] not found for comparasion.", id));
  }

  public PayloadForComparisonNotFound() {
    super("No content found for comparison.");
  }
}
