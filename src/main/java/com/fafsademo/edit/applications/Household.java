package com.fafsademo.edit.applications;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Household {
	private int numberInHousehold;
	private int numberInCollege;
	
	public Household(@JsonProperty("numberInHousehold") int numberInHousehold, @JsonProperty("numberInCollege") int numberInCollege) {
		super();
		this.numberInHousehold = numberInHousehold;
		this.numberInCollege = numberInCollege;
	}
	public int getNumberInHousehold() {
		return numberInHousehold;
	}
	public int getNumberInCollege() {
		return numberInCollege;
	}
}
