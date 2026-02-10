package com.fafsademo.edit.rules;

import com.fafsademo.edit.applications.Application;

import lombok.Getter;

@Getter
public class Rule {
	private String id;
	private String description;
	private String definition;
	private String violationMessage;
	//Derived from rule definition
	private String field;		//Literal field in application request to check
	private String operand;		//Operator to commit between field and value
	private String value;		//Value to check against - a constant or another field.
	
	
	/**
	 * Creates a new Rule
	 * @param id Name/identifier for the rule.
	 * @param description A description of what the rule's checking.
	 * @param definition A syntactical definition of the rule.
	 * @param violationMessage A message surfaced to users if the rule is violated.
	 */
	public Rule(String id, String description, String definition, String violationMessage) {
		this.id = id;
		this.description = description;
		this.definition = definition;
		this.violationMessage = violationMessage;
	}
	
	public void runRule(Application application) throws RuleViolationException {
		throw new UnsupportedOperationException();
	}
}
