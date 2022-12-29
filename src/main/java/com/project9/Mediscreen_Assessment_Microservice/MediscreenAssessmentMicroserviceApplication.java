package com.project9.Mediscreen_Assessment_Microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.project9.Mediscreen_Note_Microservice") // to scan packages mentioned
//@EnableMongoRepositories("com.project9.Mediscreen_Note_Microservice") // to activate MongoDB repositories
public class MediscreenAssessmentMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediscreenAssessmentMicroserviceApplication.class, args);
	}

}
