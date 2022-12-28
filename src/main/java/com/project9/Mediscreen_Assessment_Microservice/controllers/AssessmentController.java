package com.project9.Mediscreen_Assessment_Microservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project9.Mediscreen_Assessment_Microservice.services.AssessmentService;

@Controller
public class AssessmentController {

	@Autowired
	AssessmentService assessmentService;

	@RequestMapping("/assessment")
	public String assessmentOfPatient(Model model, String familyName) {
		model.addAttribute("assessment", assessmentService.assessmentByName(familyName));
		return "assessment";
	}

}
