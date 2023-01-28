package com.project9.Mediscreen_Assessment_Microservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.project9.Mediscreen_Assessment_Microservice.domain.Assessment;
import com.project9.Mediscreen_Assessment_Microservice.services.AssessmentService;
import com.project9.Mediscreen_Assessment_Microservice.utils.StateConstants;
import com.project9.Mediscreen_Note_Microservice.domain.Note;
import com.project9.Mediscreen_Note_Microservice.services.NoteService;
import com.project9.Mediscreen_Patient_Microservice.domain.Patient;
import com.project9.Mediscreen_Patient_Microservice.services.PatientService;

@SpringBootTest
public class AssessmentServiceTest {

	@Autowired
	private AssessmentService assessmentServiceUnderTest;
	@MockBean
	private NoteService noteServiceMocked;
	@MockBean
	private PatientService patientServiceMocked;

	Integer givenPatId;
	Patient givenMalePatient;
	Patient givenFemalePatient;
	List<Note> notes;

	@BeforeEach
	public void setup() {
		System.out.println("!!!!!!!!!!!! THIS IS SETUP BEFORE TEST !!!!!!!!!!!!");
		notes = new ArrayList<Note>();
		givenPatId = 1;
		givenMalePatient = new Patient("lastname", "firstname", Date.valueOf(LocalDate.of(1991, 6, 20)), "M", null,
				null);
		givenFemalePatient = new Patient("lastname", "firstname", Date.valueOf(LocalDate.of(1991, 6, 20)), "F", null,
				null);
		when(noteServiceMocked.findByLastnameOfPatient(givenMalePatient.getLastname())).thenReturn(notes);
		when(patientServiceMocked.findById(givenPatId)).thenReturn(givenMalePatient);
		when(patientServiceMocked.ageOfPatient(givenMalePatient.getLastname())).thenReturn(31);
		System.out.println("!!!!!!!!!!!! END OF SETUP BEFORE TEST !!!!!!!!!!!!");
	}

	@Test
	public void AssessmentNoneTest() {
		Note note1 = new Note();
		note1.setCommentary("some notes");
		Note note2 = new Note();
		note2.setCommentary("some notes");
		Note note3 = new Note();
		note3.setCommentary("some notes");

		notes.add(note1);
		notes.add(note2);
		notes.add(note3);

		Assessment result = assessmentServiceUnderTest.assessmentOfPatient(givenMalePatient, notes);

		assertThat(result.getFamilyName()).isEqualTo(givenMalePatient.getLastname());
		assertThat(result.getState()).isEqualTo(StateConstants.NONE);
	}

	@Test
	public void AssessmentBorderlineTest() {
		Note note1 = new Note();
		note1.setCommentary("some notes hémoglobine a1c");
		Note note2 = new Note();
		note2.setCommentary("some notes taille");
		Note note3 = new Note();
		note3.setCommentary("some notes");

		notes.add(note1);
		notes.add(note2);
		notes.add(note3);

		Assessment result = assessmentServiceUnderTest.assessmentOfPatient(givenMalePatient, notes);

		assertThat(result.getFamilyName()).isEqualTo(givenMalePatient.getLastname());
		assertThat(result.getState()).isEqualTo(StateConstants.BORDELINE);
	}

	@Test
	public void AssessmentManInDangerMoreThan30yoTest() {
		Note note1 = new Note();
		note1.setCommentary("some notes hémoglobine a1c");
		Note note2 = new Note();
		note2.setCommentary("some notes taille");
		Note note3 = new Note();
		note3.setCommentary("some notes Vertige");

		notes.add(note1);
		notes.add(note2);
		notes.add(note3);

		Assessment result = assessmentServiceUnderTest.assessmentOfPatient(givenMalePatient, notes);

		assertThat(result.getFamilyName()).isEqualTo(givenMalePatient.getLastname());
		assertThat(result.getState()).isEqualTo(StateConstants.IN_DANGER);
	}

	@Test
	public void AssessmentManInDangerLessThan30yoTest() {
		when(patientServiceMocked.ageOfPatient(givenMalePatient.getLastname())).thenReturn(25);

		Note note1 = new Note();
		note1.setCommentary("some notes hémoglobine a1c");
		Note note2 = new Note();
		note2.setCommentary("some notes taille");
		Note note3 = new Note();
		note3.setCommentary("some notes Vertige");

		notes.add(note1);
		notes.add(note2);
		notes.add(note3);

		Assessment result = assessmentServiceUnderTest.assessmentOfPatient(givenMalePatient, notes);

		assertThat(result.getFamilyName()).isEqualTo(givenMalePatient.getLastname());
		assertThat(result.getState()).isEqualTo(StateConstants.IN_DANGER);
	}

	@Test
	public void AssessmentManEarlyOnSetLess30yoTest() {
		when(patientServiceMocked.ageOfPatient(givenMalePatient.getLastname())).thenReturn(25);

		Note note1 = new Note();
		note1.setCommentary("some notes hémoglobine a1c");
		Note note2 = new Note();
		note2.setCommentary("some notes taille");
		Note note3 = new Note();
		note3.setCommentary("some notes Vertige");
		Note note4 = new Note();
		note4.setCommentary("some notes rechute");
		Note note5 = new Note();
		note5.setCommentary("some notes reaction");

		notes.add(note1);
		notes.add(note2);
		notes.add(note3);
		notes.add(note4);
		notes.add(note5);

		Assessment result = assessmentServiceUnderTest.assessmentOfPatient(givenMalePatient, notes);

		assertThat(result.getFamilyName()).isEqualTo(givenMalePatient.getLastname());
		assertThat(result.getState()).isEqualTo(StateConstants.EARLY_ONSET);
	}

	@Test
	public void AssessmentInDangerMoreThan30yoTest() {

		Note note1 = new Note();
		note1.setCommentary("some notes hémoglobine a1c");
		Note note2 = new Note();
		note2.setCommentary("some notes taille");
		Note note3 = new Note();
		note3.setCommentary("some notes Vertige");
		Note note4 = new Note();
		note4.setCommentary("some notes rechute");
		Note note5 = new Note();
		note5.setCommentary("some notes reaction");
		Note note6 = new Note();
		note6.setCommentary("some notes cholesterol");

		notes.add(note1);
		notes.add(note2);
		notes.add(note3);
		notes.add(note4);
		notes.add(note5);
		notes.add(note6);

		Assessment result = assessmentServiceUnderTest.assessmentOfPatient(givenMalePatient, notes);

		assertThat(result.getFamilyName()).isEqualTo(givenMalePatient.getLastname());
		assertThat(result.getState()).isEqualTo(StateConstants.IN_DANGER);
	}

	@Test
	public void AssessmentEarlyOnSetMoreThan30yoTest() {

		Note note1 = new Note();
		note1.setCommentary("some notes hémoglobine a1c");
		Note note2 = new Note();
		note2.setCommentary("some notes taille");
		Note note3 = new Note();
		note3.setCommentary("some notes Vertige");
		Note note4 = new Note();
		note4.setCommentary("some notes rechute");
		Note note5 = new Note();
		note5.setCommentary("some notes reaction");
		Note note6 = new Note();
		note6.setCommentary("some notes cholesterol");
		Note note7 = new Note();
		note7.setCommentary("some notes fumeur");
		Note note8 = new Note();
		note8.setCommentary("some notes anticorps");

		notes.add(note1);
		notes.add(note2);
		notes.add(note3);
		notes.add(note4);
		notes.add(note5);
		notes.add(note6);
		notes.add(note7);
		notes.add(note8);

		Assessment result = assessmentServiceUnderTest.assessmentOfPatient(givenMalePatient, notes);

		assertThat(result.getFamilyName()).isEqualTo(givenMalePatient.getLastname());
		assertThat(result.getState()).isEqualTo(StateConstants.EARLY_ONSET);
	}

}
