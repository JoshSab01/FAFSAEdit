package com.fafsademo.edit.rules;

import com.fafsademo.edit.applications.Application;

public class DependentRequiresParentIncomeRule extends Rule {
	public DependentRequiresParentIncomeRule() {
		super("Dependent Income Validation", "Students who are dependents must include parent income.", "true", "If student is a dependent, parental income must be included.");
	}
	
	@Override
	public void runRule(Application application) throws RuleViolationException {
		if (application.isDependent() && application.getIncome().getParentIncome() == null) {
			throw new RuleViolationException(this.getViolationMessage());
		}
	}
}
