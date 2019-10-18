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
package com.waes.diff.v1.api.swagger.config;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.not;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@Configuration @EnableSwagger2 public class SwaggerConfig {

	@Bean public Docket api() {
		return new Docket(SWAGGER_2).useDefaultResponseMessages(false).groupName("tech-assignment").apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.withClassAnnotation(Api.class)).paths(not(PathSelectors.regex("/error.*"))).build();
	}

	//  Docket apiV1()

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Encoded JSON Diff Comparison")
				.description("This is a simple server encoded JSON comparator.").license("Apache 2.0")
				.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html").termsOfServiceUrl("").version("1.0.0")
				.contact(new Contact("John Silva", "", "johnjonathann@live.com")).build();
	}
}
