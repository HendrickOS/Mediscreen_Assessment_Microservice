package com.project9.Mediscreen_Assessment_Microservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project9.Mediscreen_Assessment_Microservice.domain.Assessment;
import com.project9.Mediscreen_Assessment_Microservice.services.AssessmentService;
import com.project9.Mediscreen_Note_Microservice.domain.Note;
import com.project9.Mediscreen_Patient_Microservice.domain.Patient;

@RestController
public class AssessmentController {

	@Autowired
	AssessmentService assessmentService;

//	@GetMapping("/assessment")
//	public Assessment assessmentOfPatient(@RequestParam("patient") Patient patient,
//			@RequestParam("listOfNotes") List<Note> listOfNotes) {
//		return assessmentService.assessmentOfPatient(patient, listOfNotes);
//	}

	@GetMapping("/assessment")
	public Assessment assessmentOfPatient(@RequestParam("patient") Patient patient,
			@RequestParam("listOfNotes") List<Note> listOfNotes) {
		return assessmentService.assessmentOfPatient(patient, listOfNotes);
	}

}
