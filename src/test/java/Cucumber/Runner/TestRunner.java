package Cucumber.Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "Cucumber.Steps",
        plugin = { "pretty", "json:target/reports/cucumber.json" }
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
