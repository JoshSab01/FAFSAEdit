package com.fafsademo.edit.applications;

import com.fafsademo.edit.rules.Income;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Application {
	private Person studentInfo;
	private boolean dependent;
	private boolean married;
	private Person spouse;
	private Household household;
	private Income income;
	private String state;
	
	//Constructors for API response
	public Application(
		@JsonProperty(value="studentInfo", required=true) Person student, 
		@JsonProperty(value="dependencyStatus", required=true) String dependencyStatus, 
		@JsonProperty(value="maritalStatus", required=true) String maritalStatus, 
		@JsonProperty(value="spouseInfo", required=false) Person spouse, 
		@JsonProperty(value="household", required=true) Household household, 
		@JsonProperty(value="income", required=true) Income income, 
		@JsonProperty(value="stateOfResidence", required=true) String state) {
		
		boolean dependent = false;
		boolean married = false;
		if (dependencyStatus.equals("dependent")) {
			dependent = true;
		}
		if (maritalStatus.equals("married")) {
			married = true;
		}
		this.studentInfo = student;
		this.dependent = dependent;
		this.married = married;
		this.spouse = spouse;
		this.household = household;
		this.income = income;
		this.state = state;
	}
	
	//Constructors for testing
	public Application(Person student, boolean dependent, boolean married, Household household, Income income, String state) {
		this.studentInfo = student;
		this.dependent = dependent;
		this.married = married;
		this.household = household;
		this.income = income;
		this.state = state;
	}
	
	public Application(Person student, boolean dependent, boolean married, Person spouse, Household household, Income income, String state) {
		this(student, dependent, married, household, income, state);
		this.spouse = spouse;
	}
	
	public Person getStudentInfo() {
		return studentInfo;
	}

	public void getStudentInfo(Person student) {
		this.studentInfo = student;
	}

	public boolean isDependent() {
		return dependent;
	}

	public boolean isMarried() {
		return married;
	}

	public Person getSpouseInfo() {
		return spouse;
	}

	public Household getHousehold() {
		return household;
	}

	public Income getIncome() {
		return income;
	}

	public String getState() {
		return state;
	}
}
