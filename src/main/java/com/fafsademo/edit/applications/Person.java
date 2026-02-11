package com.fafsademo.edit.applications;

import java.time.LocalDate;
import java.time.Period;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {
	private String firstName;
	private String lastName;
	private LocalDate DOB;
	private int age;
	private String ssn;
	
	public Person(@JsonProperty("firstName") String firstName, @JsonProperty("lastName") String lastName, @JsonProperty("dateOfBirth") String DOB, @JsonProperty("ssn") String SSN) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.DOB = LocalDate.parse(DOB);
		this.ssn = SSN;
		this.age = Period.between(this.DOB, LocalDate.now()).getYears();
	}

	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}

	public LocalDate getDOB() {
		return DOB;
	}

	public int getAge() {
		return age;
	}

	public String getSSN() {
		return ssn;
	}
}
