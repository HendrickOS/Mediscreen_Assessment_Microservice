package com.project9.Mediscreen_Assessment_Microservice.domain;

public class Assessment {

	private String familyName;
	private String state;
	private int age;
	private String gender;
	private int triggerWord;

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getTriggerWord() {
		return triggerWord;
	}

	public void setTriggerWord(int triggerWord) {
		this.triggerWord = triggerWord;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
