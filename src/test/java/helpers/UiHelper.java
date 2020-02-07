package helpers;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.BookingHomePage;
import stepDefinitions.Hooks;

import java.util.List;
import java.util.logging.Logger;

import static pageobjects.BookingHomePage.*;

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

    /**
     *
     * @param textValue this is the text used to get the each value from each row
     */
    public void clickDeleteButton(String textValue){
        WebElement element = driver.findElement(By.xpath("//div[@id='bookings']/div[@class='row']/div/p[text()='"+ textValue +"']//ancestor::div[@class='row']/div[7]"));
        UiHelper.waitForVisibleElement(element);
        element.click();
    }

    /**
     * This is the helper method for dates
     * @param textValue Text value ued to verify if the text is displayed.
     * @param divIndex This is the value used in the xpath for getting the webelements
     */
    public static void datesDisplayed(String textValue, int divIndex){
        List<WebElement> elements = driver.findElements(By.xpath("//div[@id='bookings']/div[@class='row']/div["+ divIndex +"]/p"));
        for (WebElement eachElement : elements) {
            String formatDate = eachElement.getText();
            //split year, month and days from the date using StringBuffer.
            StringBuffer sBuffer = new StringBuffer(formatDate);
            String year = sBuffer.substring(2,4);
            String mon = sBuffer.substring(5,7);
            String dd = sBuffer.substring(8,10);
            if (dd.equals(textValue)){
                log.info("Date is displayed");
            }
        }
    }

    /**
     *
     * @param checkinDate string value used to select the checkinDate form the datepicker
     */
    public void checkInDate(String checkinDate) {
        check_in.click();
        List<WebElement> columns = datepicker_table.findElements(By.tagName("td"));

        for(WebElement cell : columns) {

            if(cell.getText().equals(checkinDate)){
                cell.findElement(By.linkText(checkinDate)).click();
                break;
            }}
    }

    /**
     *
     * @param checkoutDate string value used to select the checkoutDate form the datepicker
     */
    public void checkOutDate(String checkoutDate) {
        check_out.click();
//		List<WebElement> rows = datepicker_table.findElements(By.tagName("tr"));
        WebElement date = datepicker_table.findElement(By.xpath("//td[not(contains(@class,'ui-datepicker-other-month'))]/a[text()='"+ checkoutDate +"']"));
        date.click();
    }

    /**
     *
     * @param textValue Text value ued to verify if the text is displayed.
     * @param divIndex This is the value used in the xpath for getting the webelements
     */
    public void clicksavedDetailsAreDisplayed(String textValue, int divIndex){
        WebElement element = driver.findElement(By.xpath("//p[text()='"+ textValue +"']/ancestor::div[@class='row']"));
        UiHelper.waitForVisibleElement(element);
        Assert.assertTrue(element.isDisplayed());
        List<WebElement> elements = driver.findElements(By.xpath("//div[@id='bookings']/div[@class='row']/div["+ divIndex +"]/p"));
        for (WebElement eachElement : elements) {
            Assert.assertTrue(eachElement.isDisplayed());
        }
    }

    /**
     * This is the method to get the staleElement exception.
     * @param divIndex This is the value used in the xpath for getting the webelements
     * @return
     */
    public List<WebElement> retryingListElement(int divIndex) {
        List<WebElement> elements = null;
        boolean result = false;
        int attempts = 0;
        while(attempts < 2) {
            try {
                elements = driver.findElements(By.xpath("//div[@id='bookings']/div[@class='row']/div["+ divIndex +"]/p"));
                result = true;
                break;
            } catch(StaleElementReferenceException e) {
            }
            attempts++;
        }
        return elements;
    }

    /**
     *
     * @param textValue this is the text used to get the delete button for each row
     * @param divIndex this is the index of div to get the column
     */
    public void checkFirstNameIsNotDisplayed(String textValue, int divIndex) {
        WebElement element = driver.findElement(By.cssSelector("div[class='jumbotron'] h1"));
        UiHelper.waitForVisibleElement(element);
        List<WebElement> elements = retryingListElement(divIndex);
        for (WebElement elementText : elements) {
            String textDisplayed = elementText.getText();
            if (!(textDisplayed == textValue)){
                log.info("The text your are searching is not displayed in this row---->" + textDisplayed);
            }
        }

    }

}