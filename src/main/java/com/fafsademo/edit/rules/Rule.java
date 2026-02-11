package com.fafsademo.edit.rules;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import com.fafsademo.edit.applications.Application;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Rule {
	private String id;
	private String description;
	private String definition;
	private Expression parsedDefinition;
	private String violationMessage;
	//Used to actually evaluate the rule
	ExpressionParser parser;

	/**
	 * We use SpEL to power our rule behavior - custom behavior is defined here.
	 */
	static class customSpelFunctions {
		public static boolean requires(Object value) {
	        return value != null;
	    }
	}

	/**
	 * Creates a new Rule
	 * @param id Name/identifier for the rule.
	 * @param description A description of what the rule's checking.
	 * @param definition A syntactical definition of the rule.
	 * @param violationMessage A message surfaced to users if the rule is violated.
	 */
	public Rule(
			@JsonProperty("id") String id, 
			@JsonProperty("description") String description, 
			@JsonProperty("definition") String definition, 
			@JsonProperty("violationMessage") String violationMessage) {
		this.id = id;
		this.description = description;
		this.definition = definition;
		this.violationMessage = violationMessage;

		this.parser = new SpelExpressionParser();
		this.parsedDefinition = parser.parseExpression(definition);
	}

	/**
	 * Throws a RuleViolationException if the passed application violates the rule.
	 * @param application The FAFSA Application to evaluate
	 * @throws RuleViolationException If the rule is violated. A message to surface to the user is contained in the exception message.
	 * @throws NoSuchMethodException If one of our custom SpEL methods does not exist. If this is thrown something is very wrong.
	 */
	public void runRule(Application application) throws RuleViolationException, NoSuchMethodException {
		StandardEvaluationContext context = new StandardEvaluationContext(application);
		context.registerFunction(
		    "requires",
		    customSpelFunctions.class.getMethod("requires", Object.class)
		);
		if (!parsedDefinition.getValue(context, Boolean.class)) {
			throw new RuleViolationException(violationMessage);
		}
	}

	public String getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public String getViolationMessage() {
		return violationMessage;
	}
}
