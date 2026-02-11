package com.fafsademo.edit.applications;

import java.util.List;

public class ApplicationResult {
	public ApplicationResult(RESULT_STATUS status, List<String> violations) {
		super();
		this.status = status;
		this.violations = violations;
	}
	public enum RESULT_STATUS {
		SUCCESSFUL,
		UNSUCCESSFUL;
	}
	private RESULT_STATUS status;
	private List<String> violations;
	public RESULT_STATUS getStatus() {
		return status;
	}
	public List<String> getViolations() {
		return violations;
	}
}
