package com.saas.farmacia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SaasFarmaciaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaasFarmaciaApiApplication.class, args);
	}

}
