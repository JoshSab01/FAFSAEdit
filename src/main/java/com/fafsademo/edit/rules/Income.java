package com.fafsademo.edit.rules;

public class Income {
	public Income(int studentIncome) {
		this.studentIncome = studentIncome;
	}
	public Income(int studentIncome, int parentIncome) {
		this(studentIncome);
		this.parentIncome = parentIncome;
	}
	int studentIncome;
	int parentIncome;
	public Integer getStudentIncome() {
		return studentIncome;
	}
	public Integer getParentIncome() {
		return parentIncome;
	}
}
