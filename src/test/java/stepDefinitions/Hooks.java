package stepDefinitions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Hooks{

    public static WebDriver driver;

    private static Logger Log = Logger.getLogger(Hooks.class.getName());

    /**
     * This will initialize the driver instance.
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

    /**
     * This will quit the driver instance
     */
    @After
    public void quiteDriver(Scenario scenario) {
        driver.quit();
    }
    
}