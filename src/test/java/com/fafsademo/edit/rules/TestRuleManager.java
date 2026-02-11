package com.fafsademo.edit.rules;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import org.junit.jupiter.api.Test;

import com.fafsademo.edit.applications.Application;
import com.fafsademo.edit.applications.Person;
import com.fafsademo.edit.applications.Household;

public class TestRuleManager {
	//This filepath setup is a little janky as we're relying on the build to put the config file into bin and then running the unit test.
	//Unfortunately it was also the only permutation I managed to get to work, relative path from root dir. Oh well.
	private String CONFIG_PATH = "bin/config/config.json";
	
	@Test
	public void testIngest() {
		assertDoesNotThrow(() -> new RuleManager(CONFIG_PATH));
	}
	
	@Test
	public void testDefaultRulesetPasses() throws NoSuchMethodException {
		RuleManager manager = new RuleManager(CONFIG_PATH);
		
		Application passingApplication = new Application(
			new Person("Jane", "Smith", "2003-05-15", "123456789"), 
			true, 
			false, 
			new Household(4, 1), 
			new Income(5000, 65000), 
			"CA"
		);
		
		assertEquals(0, manager.runAllRules(passingApplication).size());
	}
	
	@Test
	public void testDefaultRulesetFails() throws NoSuchMethodException {
		RuleManager manager = new RuleManager(CONFIG_PATH);
		
		Application failingApplication = new Application(
			new Person("John", "Doe", "2015-01-01", "invalid"), 
			true, 
			true, 
			new Household(2, 5), 
			new Income(-1000), 
			"XX"
		);
		
		assertEquals(7, manager.runAllRules(failingApplication).size());
	}
}
