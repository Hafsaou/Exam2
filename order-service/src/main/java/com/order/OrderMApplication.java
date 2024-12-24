package com.order;

import com.order.model.Client;
import com.order.service.ClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.order.client")
@EnableDiscoveryClient
public class OrderMApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderMApplication.class, args);


	}
//	@Bean
//	public CommandLineRunner commandLineRunner(ClientService clientService){
//		System.out.println("hhhhhhhhhhhhh");
//		return (args) -> {
//			clientService.createClient(
//					Client.builder()
//							.name("prod 1").email("hafsa@gmail.com")
//							.phoneNumber("06060651")
//							.build()
//			);
//			System.out.println("hh");
//		};
//	}

}
