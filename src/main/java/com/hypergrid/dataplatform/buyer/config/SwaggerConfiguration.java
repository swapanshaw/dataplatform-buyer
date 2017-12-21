/*
 * Copyright 2015, Optimal Payments PLC, 2 Place Alexis Nihon, suite 700, Westmount, Quebec, Canada
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information of Optimal Payments PLC
 * ("Confidential Information"). You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you entered into with Optimal
 * Payments.
 */

package com.hypergrid.dataplatform.buyer.config;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableAutoConfiguration
public class SwaggerConfiguration {
  private static final Logger logger = LoggerFactory.getLogger(SwaggerConfiguration.class);

  @Bean
  public Docket sellerApi() {
    logger.info("Configuring Swagger");
    return new Docket(DocumentationType.SWAGGER_2).groupName("dataplatform-buyer")
        .apiInfo(apiInfo()).select().paths(servicePath()).build();
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder().title("Data platform sample buyer microservice")
        .description("It is a sample buyer microservice").contact(new Contact("dev", "", "hello@sample.com"))
        .version("v1").build();
  }

  @SuppressWarnings("unchecked")
  private Predicate<String> servicePath() {
    return or(regex("/v1/.*"));
  }
}