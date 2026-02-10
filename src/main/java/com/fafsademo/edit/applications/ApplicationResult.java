package com.fafsademo.edit.applications;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ApplicationResult {
	public enum RESULT_STATUS {
		SUCCESSFUL,
		UNSUCCESSFUL;
	}
	private RESULT_STATUS status;
	private List<String> violations;
}
