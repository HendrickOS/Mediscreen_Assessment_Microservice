package com.project9.Mediscreen_Assessment_Microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.project9.Mediscreen_Note_Microservice") // to scan packages mentioned
@EnableDiscoveryClient
//@EnableMongoRepositories("com.project9.Mediscreen_Note_Microservice") // to activate MongoDB repositories
public class MediscreenAssessmentMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediscreenAssessmentMicroserviceApplication.class, args);
	}

}
