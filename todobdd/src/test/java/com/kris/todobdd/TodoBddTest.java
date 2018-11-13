package com.kris.todobdd;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty", "html:target/testoutput"},
		glue = {"stepdefinition"},
		features = {"src/test/resources/features"})
public class TodoBddTest {}
