package com.fafsademo.edit.applications;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fafsademo.edit.rules.RuleManager;

import tools.jackson.databind.ObjectMapper;

public class TestApplicationController {
	private static final String CONFIG_PATH = "bin/config/config.json";
	RuleManager manager;
	@BeforeEach
	public void setUp() {
		manager = new RuleManager(CONFIG_PATH);
	}

	@Test
	public void testApplicationControllerPassesGoodApplication() {
		ObjectMapper objectMapper = new ObjectMapper();
        File jsonFile = new File("bin/resources/goodApplication.json");
        Application newApplication = objectMapper.readValue(jsonFile, Application.class);
        ApplicationController controller = new ApplicationController();
        
        ApplicationResult result = controller.newApplication(newApplication, manager);
        
        assertEquals(ApplicationResult.RESULT_STATUS.SUCCESSFUL, result.getStatus());
        assertEquals(0, result.getViolations().size());
	}
	
	@Test
	public void testApplicationControllerFailsBadApplication() {
		ObjectMapper objectMapper = new ObjectMapper();
        File jsonFile = new File("bin/resources/badApplication.json");
        Application newApplication = objectMapper.readValue(jsonFile, Application.class);
        ApplicationController controller = new ApplicationController();
        
        ApplicationResult result = controller.newApplication(newApplication, manager);
        
        assertEquals(ApplicationResult.RESULT_STATUS.UNSUCCESSFUL, result.getStatus());
        assertEquals(7, result.getViolations().size());
	}
}
