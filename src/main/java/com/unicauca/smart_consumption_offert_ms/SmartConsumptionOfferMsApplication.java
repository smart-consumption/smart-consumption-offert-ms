package com.unicauca.smart_consumption_offert_ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SmartConsumptionOfferMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartConsumptionOfferMsApplication.class, args);
	}

}
