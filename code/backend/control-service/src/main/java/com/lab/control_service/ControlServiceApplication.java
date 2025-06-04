package com.lab.control_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ControlServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControlServiceApplication.class, args);
	}

}
