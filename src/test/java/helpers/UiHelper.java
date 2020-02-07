package helpers;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stepDefinitions.Hooks;

import java.util.List;
import java.util.logging.Logger;

import static pageobjects.bookingHomePage.*;

public class UiHelper {

    public static WebDriver driver;

    public static Logger log = Logger.getLogger(String.valueOf(UiHelper.class));


    /**
     *
     * @param element This is the webelement that has to be visible on the web page.
     */
    public static void waitForVisibleElement(WebElement element){
        WebDriverWait wait = new WebDriverWait(Hooks.driver, 30);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

}