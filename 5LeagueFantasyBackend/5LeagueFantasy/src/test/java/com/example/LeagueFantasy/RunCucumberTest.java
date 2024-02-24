package com.example.LeagueFantasy;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources",
    glue = "com.example.LeagueFantasy.step_definitions",
    plugin = {
      "pretty",
      "json:target/cucumber-reports/report.json",
      "html:target/cucumber-reports/report.html"
    })
public class RunCucumberTest {}
