package com.hypergrid.dataplatform.buyer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.hypergrid.dataplatform.buyer.config.RabbitClientConfig;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {"com.hypergrid.dataplatform.buyer"})
public class DataplatformBuyerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataplatformBuyerApplication.class, args);
	}

  @Bean
  public ApplicationContext getAnnotationConfigApplicationContext() {
    return new AnnotationConfigApplicationContext(RabbitClientConfig.class);
  }

}
