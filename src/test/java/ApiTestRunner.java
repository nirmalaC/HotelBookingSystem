import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import stepDefinitions.Hooks;

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

    public static Logger log = Logger.getLogger(String.valueOf(ApiTestRunner.class));
}
