package com.project9.Mediscreen_Assessment_Microservice.services;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.project9.Mediscreen_Assessment_Microservice.domain.Assessment;
import com.project9.Mediscreen_Assessment_Microservice.utils.StateConstants;
import com.project9.Mediscreen_Note_Microservice.domain.Note;
import com.project9.Mediscreen_Patient_Microservice.domain.Patient;

@Component
public class AssessmentServiceImpl implements AssessmentService {

//	@Autowired
//	NoteService noteService;
//	@Autowired
//	PatientService patientService;

	@Override
	public Assessment assessmentOfPatient(Patient patient, List<Note> listOfNotes) {
		Assessment assessment = new Assessment();
		String nameOfPatient;
		List<String> commentariesOfEachNotesOfPatient = new ArrayList<>();
		int triggerWord = 0;
		String stateOfPatient = StateConstants.NONE;
		int ageOfPatient;
		String genderOfPatient;

		/* On récupère le nom du patient */
		nameOfPatient = patient.getLastname();

		/* On récupère les commentaires de chacune des notes du patient */
		for (Note note : listOfNotes) {
			commentariesOfEachNotesOfPatient.add(note.getCommentary());
		}

		/* On récupère le genre du patient */
		genderOfPatient = patient.getGender();

		/*
		 * On compte le nombre de mot clé présent dans chacun des commentaires
		 * concernant le patient
		 */
		for (String commentary : commentariesOfEachNotesOfPatient) {
			if (commentary.contains("Hémoglobine A1C")) {
				triggerWord = triggerWord + 1;
			}
			if (commentary.contains("Microalbumine")) {
				triggerWord = triggerWord + 1;
			}
			if (commentary.contains("Taille")) {
				triggerWord = triggerWord + 1;
			}
			if (commentary.contains("Poids")) {
				triggerWord = triggerWord + 1;
			}
			if (commentary.contains("Fumeur")) {
				triggerWord = triggerWord + 1;
			}
			if (commentary.contains("Anormal")) {
				triggerWord = triggerWord + 1;
			}
			if (commentary.contains("Cholestérol")) {
				triggerWord = triggerWord + 1;
			}
			if (commentary.contains("Vertige")) {
				triggerWord = triggerWord + 1;
			}
			if (commentary.contains("Rechute")) {
				triggerWord = triggerWord + 1;
			}
			if (commentary.contains("Réaction")) {
				triggerWord = triggerWord + 1;
			}
			if (commentary.contains("Anticorps")) {
				triggerWord = triggerWord + 1;
			}
		}

		/* On récupère l'âge du patient */
		ageOfPatient = Period.between(patient.getBirthdate().toLocalDate(), LocalDate.now()).getYears();

		/* On donne l'état patient */
		if (triggerWord == 0) {
			stateOfPatient = StateConstants.NONE;
		}

		if (triggerWord >= 2 && triggerWord < 6) {
			if (ageOfPatient > 30) {
				stateOfPatient = StateConstants.BORDELINE;
			}
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
			if (triggerWord >= 6 && triggerWord < 8) {
				stateOfPatient = StateConstants.IN_DANGER;
			}
			if (triggerWord >= 8) {
				stateOfPatient = StateConstants.EARLY_ONSET;
			}

		}

		/* On set toutes les informations dans un assessment */
		assessment.setFamilyName(nameOfPatient);
		assessment.setState(stateOfPatient);
		assessment.setAge(ageOfPatient);
		assessment.setGender(genderOfPatient);
		assessment.setTriggerWord(triggerWord);

		return assessment;
	}

}
