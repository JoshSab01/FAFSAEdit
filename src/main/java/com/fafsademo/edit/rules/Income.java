package com.fafsademo.edit.rules;

public class Income {
	public Income(int studentIncome) {
		this.studentIncome = studentIncome;
	}
	public Income(Integer studentIncome, Integer parentIncome) {
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
