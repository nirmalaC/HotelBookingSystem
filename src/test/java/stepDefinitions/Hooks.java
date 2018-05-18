package stepDefinitions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import lombok.experimental.Helper;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import lombok.Getter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Hooks{

    @Getter
    public static WebDriver driver;

    private static Logger Log = Logger.getLogger(Hooks.class.getName());


    /**
     * Delete all cookies at the start of each scenario to avoid
     */
    @Before
    public void openBrowser() throws IOException {
        Properties property = Helpers.readPropertisFile();
            String browser = property.getProperty("browser");
            if(browser.equalsIgnoreCase("firefox")){
                System.setProperty("webdriver.gecko.driver",property.getProperty("geckoDriver"));
                driver = new FirefoxDriver();
            } else if(browser.equalsIgnoreCase("IE")){
                System.setProperty("webdriver.ie.driver",property.getProperty("ieDriver"));
                driver = new InternetExplorerDriver();
            } else if(browser.equalsIgnoreCase("chrome")){
                System.setProperty("webdriver.chrome.driver", property.getProperty("chromeDriver"));
                driver = new ChromeDriver();
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
            driver.get(property.getProperty("url"));
    }

    @After
    /**
     * Embed a screenshot in test report if test is marked as failed
     */
    public void quiteDriver(Scenario scenario) {
        driver.quit();
    }
    
}