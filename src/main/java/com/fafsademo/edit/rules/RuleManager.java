package com.fafsademo.edit.rules;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.fafsademo.edit.applications.Application;
import com.fafsademo.edit.rules.Rule;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@Component
public class RuleManager {
	private Set<Rule> rules;
	
	//In a real context this would be handled by AppConfig, but standing up AWS architecture is too much here.
	private final static String DEFAULT_CONFIG_PATH = "bin/config/config.json";
	
	public RuleManager() {
		this(DEFAULT_CONFIG_PATH);
	}
	
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
