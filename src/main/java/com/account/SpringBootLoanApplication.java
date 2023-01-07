package com.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@RefreshScope
public class SpringBootLoanApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootLoanApplication.class, args);
	}

}
