package com.surya.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
  features = "src/test/resources/features",
  glue = {"com.surya.stepdefinitions", "com.surya.hooks"},
  plugin = {"pretty", "html:target/cucumber-reports.html"},
  monochrome = true
)

public class TestRunner {
}
