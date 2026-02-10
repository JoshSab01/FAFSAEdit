package com.fafsademo.edit.rules;

public class RuleViolationException extends Exception {
	public RuleViolationException(String messageToUser) {
		super(messageToUser);
	}
}
