package com.fafsademo.edit.rules;

import java.util.List;
import java.util.Set;

import com.fafsademo.edit.applications.Application;
import com.fafsademo.edit.rules.Rule;

public class RuleManager {
	private Set<Rule> rules;
	
	/**
	 * Creates a new RuleManager with rules derived from the passed config file.
	 * @param rulesFilepath The rules file to ingest.
	 */
	public RuleManager(String rulesFilepath) {
		this.rules = ingestRulesFromFile(rulesFilepath);
	}
	
	/**
	 * A setup function which pulls rule definitions from a config file.
	 */
	private Set<Rule> ingestRulesFromFile(String filepath) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Runs all rules over a given application and returns a list of all violations in the application
	 * @param application The application to check.
	 * @return A list of String messages describing each problem with the application.
	 */
	public List<String> runAllRules(Application application) {
		throw new UnsupportedOperationException();
	}
}
