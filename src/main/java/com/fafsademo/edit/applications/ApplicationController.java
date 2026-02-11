package com.fafsademo.edit.applications;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fafsademo.edit.rules.RuleManager;

@RestController
public class ApplicationController {
	@PostMapping("/application")
	ApplicationResult newApplication(@RequestBody Application newApplication, RuleManager ruleManager) {
		try {
			List<String> violations = ruleManager.runAllRules(newApplication);
			if (violations.size() == 0) {
			return new ApplicationResult(ApplicationResult.RESULT_STATUS.SUCCESSFUL, violations);
			} else {
				return  new ApplicationResult(ApplicationResult.RESULT_STATUS.UNSUCCESSFUL, violations);
			}
		} catch (NoSuchMethodException e) {
			throw new RuntimeException("An internal server error occured. Please try again later.");
			//TODO: Log a metric the team should alarm on. This error should NEVER occur, something is terribly wrong.
		}
	}
}
