package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = { "stepdefinitions", "hooks" }, plugin = { "pretty",
		"html:target/cucumber-reports/cucumber-html-report.html", "json:target/cucumber-reports/cucumber.json",
		"junit:target/cucumber-reports/cucumber.xml" }, publish = true, monochrome = true, tags = "@API")

public class TestRunner {
}
