package com.fafsademo.edit.rules;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Income {
	public Income(int studentIncome) {
		this.studentIncome = studentIncome;
	}
	public Income(@JsonProperty("studentIncome") Integer studentIncome, @JsonProperty("parentIncome") Integer parentIncome) {
		this(studentIncome);
		this.parentIncome = parentIncome;
	}
	int studentIncome;
	Integer parentIncome;
	public Integer getStudentIncome() {
		return studentIncome;
	}
	public Integer getParentIncome() {
		return parentIncome;
	}
}
