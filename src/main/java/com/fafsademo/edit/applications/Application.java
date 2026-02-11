package com.fafsademo.edit.applications;

import com.fafsademo.edit.rules.Income;

public class Application {
	private Person studentInfo;
	private boolean dependent;
	private boolean married;
	private Person spouse;
	private Household household;
	private Income income;
	private String state;
	
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

	public void setDependent(boolean dependent) {
		this.dependent = dependent;
	}

	public boolean isMarried() {
		return married;
	}

	public void setMarried(boolean married) {
		this.married = married;
	}

	public Person getSpouse() {
		return spouse;
	}

	public void setSpouse(Person spouse) {
		this.spouse = spouse;
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

	public void setState(String state) {
		this.state = state;
	}
}
