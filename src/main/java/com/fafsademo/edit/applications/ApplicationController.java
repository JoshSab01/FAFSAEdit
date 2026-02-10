package com.fafsademo.edit.applications;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {
	@PostMapping("/application")
	ApplicationResult newApplication(@RequestBody Application newApplication) {
		throw new UnsupportedOperationException();
	}
}
