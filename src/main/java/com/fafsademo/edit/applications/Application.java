package com.fafsademo.edit.applications;

public class Application {
	private Person student;
	private boolean dependent;
	private boolean married;
	private Person spouse;
	private int householdMembers;
	private int householdMembersInCollege;
	private int income;
	private String state;
	
	public Application(Person student, boolean dependent, boolean married, int householdMembers, int householdMembersInCollege, int income, String state) {
		this.student = student;
		this.dependent = dependent;
		this.married = married;
		this.householdMembers = householdMembers;
		this.householdMembersInCollege = householdMembersInCollege;
		this.income = income;
		this.state = state;
	}
	
	public Application(Person student, boolean dependent, boolean married, Person spouse, int householdMembers, int householdMembersInCollege, int income, String state) {
		this(student, dependent, married, householdMembers, householdMembersInCollege, income, state);
		this.spouse = spouse;
	}
	
	public Person getStudent() {
		return student;
	}

	public void setStudent(Person student) {
		this.student = student;
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

	public int getHouseholdMembers() {
		return householdMembers;
	}

	public void setHouseholdMembers(int householdMembers) {
		this.householdMembers = householdMembers;
	}

	public int getHouseholdMembersInCollege() {
		return householdMembersInCollege;
	}

	public void setHouseholdMembersInCollege(int householdMembersInCollege) {
		this.householdMembersInCollege = householdMembersInCollege;
	}

	public int getIncome() {
		return income;
	}

	public void setIncome(int income) {
		this.income = income;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
