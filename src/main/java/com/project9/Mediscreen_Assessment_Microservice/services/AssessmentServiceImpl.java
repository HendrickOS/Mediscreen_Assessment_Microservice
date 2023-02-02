package com.project9.Mediscreen_Assessment_Microservice.services;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.project9.Mediscreen_Assessment_Microservice.domain.Assessment;
import com.project9.Mediscreen_Assessment_Microservice.domain.Note;
import com.project9.Mediscreen_Assessment_Microservice.domain.Patient;
import com.project9.Mediscreen_Assessment_Microservice.utils.StateConstants;

@Component
public class AssessmentServiceImpl implements AssessmentService {

	@Override
	public Assessment assessmentOfPatient(Patient patient, List<Note> listOfNotes) {
		Assessment assessment = new Assessment();
		String nameOfPatient;
		List<String> commentariesOfEachNotesOfPatient = new ArrayList<>();
		int triggerWord = 0;
		String stateOfPatient = StateConstants.NONE;
		int ageOfPatient;
		String genderOfPatient;

		nameOfPatient = patient.getLastname();

		for (Note note : listOfNotes) {
			commentariesOfEachNotesOfPatient.add(note.getCommentary());
		}

		genderOfPatient = patient.getGender();

		for (String commentary : commentariesOfEachNotesOfPatient) {
			if (commentary.contains("Hemoglobine A1C") || commentary.contains("hemoglobine a1c")) {
				triggerWord = triggerWord + 1;
			}
			if (commentary.contains("Microalbumine") || commentary.contains("microalbumine")) {
				triggerWord = triggerWord + 1;
			}
			if (commentary.contains("Taille") || commentary.contains("taille")) {
				triggerWord = triggerWord + 1;
			}
			if (commentary.contains("Poids") || commentary.contains("poids")) {
				triggerWord = triggerWord + 1;
			}
			if (commentary.contains("Fumeur") || commentary.contains("fumeur")) {
				triggerWord = triggerWord + 1;
			}
			if (commentary.contains("Anormal") || commentary.contains("anormal")) {
				triggerWord = triggerWord + 1;
			}
			if (commentary.contains("Cholesterol") || commentary.contains("cholesterol")) {
				triggerWord = triggerWord + 1;
			}
			if (commentary.contains("Vertige") || commentary.contains("vertige")) {
				triggerWord = triggerWord + 1;
			}
			if (commentary.contains("Rechute") || commentary.contains("rechute")) {
				triggerWord = triggerWord + 1;
			}
			if (commentary.contains("Reaction") || commentary.contains("reaction")) {
				triggerWord = triggerWord + 1;
			}
			if (commentary.contains("Anticorps") || commentary.contains("anticorps")) {
				triggerWord = triggerWord + 1;
			}
		}

		ageOfPatient = Period.between(patient.getBirthdate().toLocalDate(), LocalDate.now()).getYears();

		if (triggerWord == 0) {
			stateOfPatient = StateConstants.NONE;
		}

		if (genderOfPatient.equals("F") && ageOfPatient < 30) {
			if (triggerWord >= 4 && triggerWord < 7) {
				stateOfPatient = StateConstants.IN_DANGER;
			}
			if (triggerWord >= 7) {
				stateOfPatient = StateConstants.EARLY_ONSET;
			}
		}
		if (genderOfPatient.equals("M") && ageOfPatient < 30) {
			if (triggerWord >= 3 && triggerWord < 5) {
				stateOfPatient = StateConstants.IN_DANGER;
			}
			if (triggerWord >= 5) {
				stateOfPatient = StateConstants.EARLY_ONSET;
			}
		}
		if (ageOfPatient > 30) {
			if (triggerWord >= 2 && triggerWord < 6) {
				stateOfPatient = StateConstants.BORDERLINE;
			}
			if (triggerWord >= 6 && triggerWord < 8) {
				stateOfPatient = StateConstants.IN_DANGER;
			}
			if (triggerWord >= 8) {
				stateOfPatient = StateConstants.EARLY_ONSET;
			}

		}

		assessment.setFamilyName(nameOfPatient);
		assessment.setState(stateOfPatient);
		assessment.setAge(ageOfPatient);
		assessment.setGender(genderOfPatient);
		assessment.setTriggerWord(triggerWord);

		return assessment;
	}

}
