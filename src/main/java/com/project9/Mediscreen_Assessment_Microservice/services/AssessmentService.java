package com.project9.Mediscreen_Assessment_Microservice.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project9.Mediscreen_Assessment_Microservice.domain.Assessment;
import com.project9.Mediscreen_Note_Microservice.domain.Note;
import com.project9.Mediscreen_Patient_Microservice.domain.Patient;

@Service
public interface AssessmentService {

	Assessment assessmentOfPatient(Patient patient, List<Note> listOfNotes);

}
