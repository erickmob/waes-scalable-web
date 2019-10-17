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
package com.waes.diff.v1.api.repository.entity;

import com.waes.diff.v1.api.domain.enums.Direction;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

import static lombok.AccessLevel.PRIVATE;

@Data @Document(collection = "payloads") @CompoundIndex(useGeneratedName = true, def = "{'id': 1, 'direction': 1, 'content': 1 }", unique = true) @NoArgsConstructor(access = PRIVATE) public class Payload {
	@Id private String uid;

	@NotBlank(message = "Id is mandatory") private String id;

	@NotBlank(message = "ID can not be null or empty") private Direction direction;

	@NotBlank(message = "Content is mandatory") private String content;

	public static Payload create() {
		return new Payload();
	}

	public Payload id(String id) {
		this.id = id;
		return this;
	}

	public Payload direction(Direction direction) {
		this.direction = direction;
		return this;
	}

	public Payload content(String content) {
		this.content = content;
		return this;
	}
}
