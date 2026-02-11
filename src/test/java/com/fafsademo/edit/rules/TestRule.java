package com.fafsademo.edit.rules;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.fafsademo.edit.applications.Application;
import com.fafsademo.edit.applications.Household;
import com.fafsademo.edit.applications.Person;

/**
 * We use SpEL to power our rule definitions - but we only have four operands that we really care about right now:
 * >
 * <
 * matches
 * requires (a custom keyword which checks for the existence of another field)
 */
class TestRule {
	
    @Test
    void nestedGreaterThanPassesWhenTrue() throws Exception {
    	Person student = new Person("Person", "2000-01-01", "SSN");
    	Household household = new Household(3, 1);
    	Income income = new Income(50000);
        Application app = new Application(
            student,
            true,
            false,
            household,
            income,
            "CA"
        );

        Rule rule = new Rule(
            "StudentMinimumAgeRule",
            "Student must be older than 14",
            "studentInfo.age > 14",
            "Student must be older than 14"
        );

        assertDoesNotThrow(() -> rule.runRule(app));
    }

    @Test
    void test_household_rule() throws Exception {
    	Person student = new Person("Person", "2026-01-01", "SSN");
    	Household household = new Household(1, 18);
    	Income income = new Income(50000);
        Application app = new Application(
            student,
            true,
            false,
            household,
            income,
            "CA"
        );

        Rule rule = new Rule(
            "HouseholdInCollegeLessThanHousehold",
            "Must have less household members in college than household members",
            "household.numberInHousehold >= household.numberInCollege",
            "Cannot have more household members in college than in household."
        );

        RuleViolationException ex =
                assertThrows(RuleViolationException.class, () -> rule.runRule(app));

        assertEquals("Cannot have more household members in college than in household.", ex.getMessage());
    }


    @Test
    void verify_Regex_capture() throws Exception {
    	Person student = new Person("Person", "2000-01-01", "SSN");
    	Household household = new Household(3, 1);
    	Income income = new Income(50000);
        Application app = new Application(
            student,
            true,
            false,
            household,
            income,
            "CA"
        );
        Rule rule = new Rule(
            "StateValid",
            "State must be given in two-letter code",
            "state matches '(?:AL|AK|AZ|AR|CA|CO|CT|DE|FL|GA|HI|ID|IL|IN|IA|KS|KY|LA|ME|MD|MA|MI|MN|MS|MO|MT|NV|NH|NJ|NM|NY|NC|ND|OH|OK|OR|PA|RI|SC|SD|TN|TX|UT|VT|VA|WA|WV|WI|WY|NE)'",
            "State must be given in two-letter code"
        );

        assertDoesNotThrow(() -> rule.runRule(app));
    }
    
    @Test
    void validate_ssn_rule() throws Exception {
    	Person student = new Person("Person", "2000-01-01", "123456789");
    	Household household = new Household(3, 1);
    	Income income = new Income(50000);
        Application app = new Application(
            student,
            true,
            false,
            household,
            income,
            "CA"
        );
        Rule rule = new Rule(
            "SSNValid",
            "SSN must be a string of 9 numbers.",
            "studentInfo.SSN matches '^\\d{9}$'",
            "SSN is improperly formatted. Type your SSN as a string of 9 numbers. No dashes, no other characters."
        );

        assertDoesNotThrow(() -> rule.runRule(app));
    }

    @Test
    void requires_fails_whenFieldMissing() throws Exception {
    	Person student = new Person("Person", "2000-01-01", null);
    	Household household = new Household(3, 1);
    	Income income = new Income(50000);
        Application app = new Application(
            student,
            true,
            false,
            household,
            income,
            "CA"
        );
        Rule rule = new Rule(
            "REQUIRES_SSN",
            "SSN required",
            "#requires(studentInfo.SSN)",
            "SSN is required"
        );

        RuleViolationException ex =
            assertThrows(RuleViolationException.class, () -> rule.runRule(app));

        assertEquals("SSN is required", ex.getMessage());
    }

    @Test
    void requires_passes_whenFieldPresent() {
    	Person student = new Person("Person", "2000-01-01", "SSN");
    	Household household = new Household(1, 1);
    	Income income = new Income(50000);
        Application app = new Application(
            student,
            false,
            true,  // married
            household,
            income,
            "CA"
        );

        Rule rule = new Rule(
	        "REQUIRES_SSN",
	        "SSN required",
	        "#requires(studentInfo.SSN)",
	        "SSN is required"
	    );

        assertDoesNotThrow(() -> rule.runRule(app));
    }
}
