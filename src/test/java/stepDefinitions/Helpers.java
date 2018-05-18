package stepDefinitions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Helpers {

    public static Properties readPropertisFile() throws IOException {
        FileInputStream fis = null;
        fis = new FileInputStream("C:\\HotelBookingSystem\\src\\test\\resources\\Config.properties");
        Properties property = new Properties();
        property.load(fis);
        return property;
    }

    public void waitForVisibleElement(WebElement element){
        WebDriverWait wait = new WebDriverWait(Hooks.driver, 30);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementClickable(WebElement element){
        WebDriverWait wait = new WebDriverWait(Hooks.driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }



}