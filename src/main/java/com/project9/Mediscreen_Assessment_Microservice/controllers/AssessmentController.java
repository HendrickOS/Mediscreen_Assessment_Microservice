package com.project9.Mediscreen_Assessment_Microservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project9.Mediscreen_Assessment_Microservice.domain.Assessment;
import com.project9.Mediscreen_Assessment_Microservice.services.AssessmentService;

@RestController
public class AssessmentController {

	@Autowired
	AssessmentService assessmentService;

	@GetMapping("/assessment")
	public Assessment assessmentOfPatient(@RequestParam String familyName) {
		return assessmentService.assessmentByName(familyName);
	}

}
