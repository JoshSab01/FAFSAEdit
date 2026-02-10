package com.fafsademo.edit.applications;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Application {
	private Person student;
	private boolean dependent;
	private boolean married;
	private Person spouse;
}
