package com.project9.Mediscreen_Assessment_Microservice.services;

import org.springframework.stereotype.Service;

import com.project9.Mediscreen_Assessment_Microservice.domain.Assessment;

@Service
public interface AssessmentService {

	Assessment assessmentByName(String nameOfPatient);

}
