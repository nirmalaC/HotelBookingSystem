import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/Features",
		glue={"stepDefinitions"},
		monochrome = true,
		plugin = { "pretty", "html:target/cucumber-reports" },
		tags = {"@FeatureAutomationTest", "~@ManualTests"}
		)
public class TestRunner {


	
}