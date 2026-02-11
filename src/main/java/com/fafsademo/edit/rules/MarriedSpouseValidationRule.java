package com.fafsademo.edit.rules;

import com.fafsademo.edit.applications.Application;

public class MarriedSpouseValidationRule extends Rule {
	public MarriedSpouseValidationRule() {
		super("Married Spouse Validation", "Married students must include spouse info.", "true", "If student is married, spouse information must be included.", 1);
	}
	
	@Override
	public void runRule(Application application) throws RuleViolationException {
		if (application.isMarried() && application.getSpouse() == null) {
			throw new RuleViolationException(this.getViolationMessage());
		}
	}
}
