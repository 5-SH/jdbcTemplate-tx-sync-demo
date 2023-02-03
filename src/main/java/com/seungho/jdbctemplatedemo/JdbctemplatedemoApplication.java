package com.seungho.jdbctemplatedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class JdbctemplatedemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JdbctemplatedemoApplication.class, args);
	}

}
