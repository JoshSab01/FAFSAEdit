package com.fafsademo.edit.applications;

import java.util.List;

public class ApplicationResult {
	public enum RESULT_STATUS {
		SUCCESSFUL,
		UNSUCCESSFUL;
	}
	private RESULT_STATUS status;
	private List<String> violations;
}
