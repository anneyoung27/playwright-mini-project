package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepDefinitions"},
        plugin = {"pretty", "json:reports/cucumber_report.json", "html:reports/cucumber_report.html"}
)
public class RunnerTest extends AbstractTestNGCucumberTests {
}
