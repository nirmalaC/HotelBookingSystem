import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


import java.util.logging.Logger;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/Features",
        glue={"stepDefinitions"},
        monochrome = true,
        plugin = { "pretty", "html:target/cucumber-reports" },
        tags = {"@ApiTests", "~@FeatureAutomationTest", "~@ManualTests"}
)
public class ApiTestRunner {
}
