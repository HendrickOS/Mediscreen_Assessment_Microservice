package com.project9.Mediscreen_Assessment_Microservice.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "notes")
public class Note {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	private String lastnameOfPatient;
	private String commentary;

	public Note() {
		super();
	}

	public Note(String id, String lastnameOfPatient, String commentary) {
		super();
		this.id = id;
		this.lastnameOfPatient = lastnameOfPatient;
		this.commentary = commentary;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLastnameOfPatient() {
		return lastnameOfPatient;
	}

	public void setLastnameOfPatient(String lastnameOfPatient) {
		this.lastnameOfPatient = lastnameOfPatient;
	}

	public String getCommentary() {
		return commentary;
	}

	public void setCommentary(String commentary) {
		this.commentary = commentary;
	}

}
