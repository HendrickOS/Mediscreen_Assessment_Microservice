package com.project9.Mediscreen_Assessment_Microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableDiscoveryClient
public class MediscreenAssessmentMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediscreenAssessmentMicroserviceApplication.class, args);
	}

}
