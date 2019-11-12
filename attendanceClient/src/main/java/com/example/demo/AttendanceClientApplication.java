package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.demo.controller.AttendanceClientController;


@SpringBootApplication
@EnableDiscoveryClient
public class AttendanceClientApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(AttendanceClientApplication.class, args);
		AttendanceClientController client = ctx.getBean(AttendanceClientController.class);
		System.out.println(client.students());
	}

}	
	
