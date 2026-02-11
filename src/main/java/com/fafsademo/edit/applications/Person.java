package com.fafsademo.edit.applications;

import java.time.LocalDate;
import java.time.Period;

public class Person {
	private String name;
	private LocalDate DOB;
	private int age;
	private String SSN;
	
	public Person(String name, String DOB, String SSN) {
		this.name = name;
		this.DOB = LocalDate.parse(DOB);
		this.SSN = SSN;
		this.age = Period.between(this.DOB, LocalDate.now()).getYears();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDOB() {
		return DOB;
	}

	public void setDOB(LocalDate dOB) {
		DOB = dOB;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSSN() {
		return SSN;
	}

	public void setSSN(String sSN) {
		SSN = sSN;
	}
}
