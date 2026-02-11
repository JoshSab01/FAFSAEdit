package com.fafsademo.edit.rules;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fafsademo.edit.applications.Application;
import com.fafsademo.edit.rules.Rule;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

public class RuleManager {
	private Set<Rule> rules;
	
	/**
	 * Creates a new RuleManager with rules derived from the passed config file.
	 * @param rulesFilepath The rules file to ingest.
	 */
	public RuleManager(String rulesFilepath) {
		this.rules = ingestRulesFromFile(rulesFilepath);
		this.rules.add(new MarriedSpouseValidationRule());
		this.rules.add(new DependentRequiresParentIncomeRule());
	}
	
	/**
	 * A setup function which pulls rule definitions from a config file.
	 */
	private Set<Rule> ingestRulesFromFile(String filepath) {
		rules = new HashSet<Rule>();
		try {
            ObjectMapper objectMapper = new ObjectMapper();
            File jsonFile = new File(filepath);
            
            Rule[] rulesArray = objectMapper.readValue(jsonFile, Rule[].class);
            for (Rule rule : rulesArray) {
            	rules.add(rule);
            }
        } catch (Exception e) {
            throw new RuntimeException("There was an error while parsing the config file.", e);
        }
		return rules;
	}
	
	/**
	 * Runs all rules over a given application and returns a list of all violations in the application
	 * @param application The application to check.
	 * @return A list of String messages describing each problem with the application.
	 */
	public List<String> runAllRules(Application application) throws NoSuchMethodException {
		List<String> violations = new ArrayList<String>();
		for (Rule rule : rules) {
			try {
				rule.runRule(application);
			}
			catch (RuleViolationException e) {
				violations.add(e.getMessage());
			}
		}
		return violations;
	}
}
