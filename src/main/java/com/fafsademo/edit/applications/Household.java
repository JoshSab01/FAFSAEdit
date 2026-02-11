package com.fafsademo.edit.applications;

public class Household {
	private int numberInHousehold;
	private int numberInCollege;
	
	public Household(int numberInHousehold, int numberInCollege) {
		super();
		this.numberInHousehold = numberInHousehold;
		this.numberInCollege = numberInCollege;
	}
	public int getNumberInHousehold() {
		return numberInHousehold;
	}
	public void setNumberInHousehold(int numberInHousehold) {
		this.numberInHousehold = numberInHousehold;
	}
	public int getNumberInCollege() {
		return numberInCollege;
	}
	public void setNumberInCollege(int numberInCollege) {
		this.numberInCollege = numberInCollege;
	}
}
