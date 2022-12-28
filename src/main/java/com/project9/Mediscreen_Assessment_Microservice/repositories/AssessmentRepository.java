package com.project9.Mediscreen_Assessment_Microservice.repositories;

import com.project9.Mediscreen_Assessment_Microservice.domain.Assessment;

public interface AssessmentRepository {

	Assessment assessmentOfPatient(String nameOfPatient);

}
