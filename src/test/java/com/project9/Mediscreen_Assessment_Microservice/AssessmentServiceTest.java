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
		givenMalePatient = new Patient("name", "fullname", Date.valueOf(LocalDate.of(1991, 6, 20)), "M", null, null);
		givenFemalePatient = new Patient("name", "fullname", Date.valueOf(LocalDate.of(1991, 6, 20)), "F", null, null);
		when(noteServiceMocked.findByPatientsName(givenMalePatient.getFullname())).thenReturn(notes);
		when(patientServiceMocked.findById(givenPatId)).thenReturn(givenMalePatient);
		when(patientServiceMocked.ageOfPatient(givenMalePatient.getFullname())).thenReturn(31);
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
		notes.addAll(List.of(note1, note2, note3));

		Assessment result = assessmentServiceUnderTest.assessmentOfPatient(givenMalePatient, notes);

		assertThat(result.getFamilyName()).isEqualTo(givenMalePatient.getFullname());
		assertThat(result.getState()).isEqualTo(StateConstants.NONE);
	}

}
