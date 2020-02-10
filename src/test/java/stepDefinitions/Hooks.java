package stepDefinitions;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import helpers.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Hooks {

    public static WebDriver driver;

    public static Logger log = Logger.getLogger(String.valueOf(Hooks.class));

    public String fs = File.separator;

    public static Properties configProperties;

    public static String browser;

    public Hooks() throws IOException {
        configProperties = Utils.readPropertiesFile();
        browser = configProperties.getProperty("browser");
    }

    /**
     * This will initialize the driver instance.
     */
    @Before("~@ApiTests")
    public void openBrowser() {

        log.info("Test Started");

        if (getOS().contains("WINDOWS")) {

            switch (browser) {
                case "chrome":
                    String winChromeDriver = "src" + fs + "test" + fs + "resources" + fs + "Drivers" + fs + "ChromeDriver" + fs + "chromedriver.exe";
                    String winChromePath = Paths.get(winChromeDriver).toAbsolutePath().toString();
                    System.setProperty("webdriver.chrome.driver", winChromePath);
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    String winFirefoxDriver = "src" + fs + "test" + fs + "resources" + fs + "Drivers" + fs + "GeckoDriver" + fs + "geckodriver.exe";
                    String winFirefoixPath = Paths.get(winFirefoxDriver).toAbsolutePath().toString();
                    System.setProperty("webdriver.gecko.driver", winFirefoixPath);
                    driver = new FirefoxDriver();
                    break;
            }
            initiateWebdriver();
        } else if (getOS().contains("MAC")) {

            switch (browser) {
                case "chrome":
                    String macChromeDriver = "src" + fs + "test" + fs + "resources" + fs + "Drivers" + fs + "ChromeDriver" + fs + "chromedriver";
                    String macChromePath = Paths.get(macChromeDriver).toAbsolutePath().toString();
                    System.setProperty("webdriver.chrome.driver", macChromePath);
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    String macFirefixDriver = "src" + fs + "test" + fs + "resources" + fs + "Drivers" + fs + "GeckoDriver" + fs + "geckodriver";
                    String macFirefoxPath = Paths.get(macFirefixDriver).toAbsolutePath().toString();
                    System.setProperty("webdriver.gecko.driver", macFirefoxPath);
                    driver = new FirefoxDriver();
                    break;
            }
            initiateWebdriver();
        }
    }

    /**
     * This will quit the driver instance
     */
    @After("~@ApiTests")
    public void quitDriver() {
        driver.quit();
        log.info("Test Finished");

    }

    public static String getOS() {
        return System.getProperty("os.name").toUpperCase();
    }

    public static void initiateWebdriver() {
        log.info("Opening Browser...." + browser);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(configProperties.getProperty("url"));
    }

}