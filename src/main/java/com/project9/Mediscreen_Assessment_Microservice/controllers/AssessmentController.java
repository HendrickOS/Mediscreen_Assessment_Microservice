package com.project9.Mediscreen_Assessment_Microservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project9.Mediscreen_Assessment_Microservice.domain.Assessment;
import com.project9.Mediscreen_Assessment_Microservice.domain.PatientAssessment;
import com.project9.Mediscreen_Assessment_Microservice.services.AssessmentService;

@RestController
public class AssessmentController {

	@Autowired
	AssessmentService assessmentService;

	@PostMapping("/assessment")
	public Assessment assessmentOfPatient(@RequestBody PatientAssessment patientAssessment) {
		return assessmentService.assessmentOfPatient(patientAssessment.getPatientBean(),
				patientAssessment.getListOfNotesBean());
	}

}
