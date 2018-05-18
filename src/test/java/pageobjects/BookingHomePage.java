package pageobjects;

import org.hamcrest.Matcher;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import stepDefinitions.Hooks;

import java.util.List;
import java.util.NoSuchElementException;


public class BookingHomePage extends Hooks{

	@FindBy(how= How.ID, using="firstname")
	public static WebElement first_name;
	
	@FindBy(how= How.ID, using="lastname")
	public static WebElement sure_name;
	
	@FindBy(how= How.ID, using="totalprice")
	public static WebElement price;

	@FindBy(how= How.ID, using="depositpaid")
	public WebElement deposit;

	@FindBy(how= How.ID, using="checkin")
	public static WebElement check_in;

	@FindBy(how= How.ID, using="checkout")
	public static WebElement check_out;

	@FindBy(how= How.CSS, using="input[onclick='createBooking()'] ")
	public static WebElement save_button;

	@FindBy(how= How.CSS, using="input[value='Delete']")
	public static WebElement delete_button;

	@FindBy(how= How.CSS, using="table[class='ui-datepicker-calendar']")
	public static WebElement datepicker_table;

	@FindBy(how= How.CSS, using="div[class='ui-datepicker-title']")
	public static WebElement datepicker_title;

	@FindBy(how= How.CSS, using="a[data-handler='prev']")
	public static WebElement datepicker_previous;

	@FindBy(how= How.CSS, using="a[data-handler='next']")
	public static WebElement datepicker_next;

	/**
	 *
	 * @param bookingDeposit value used to select the deposit value form the dropdown field
	 */
	public void enterDeposit(String bookingDeposit){
		Select dropDown = new Select(deposit);
		dropDown.selectByVisibleText(bookingDeposit);
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
		List<WebElement> rows = datepicker_table.findElements(By.tagName("tr"));
		List<WebElement> columns = datepicker_table.findElements(By.tagName("td"));

		for(WebElement cell : columns) {

			if(cell.getText().equals(checkoutDate)){
				cell.findElement(By.linkText(checkoutDate)).click();
				break;
			}}
	}

	/**
	 *
	 * @param firstname The firstname parameter is the text to enter in the firstname textbox while saving booking
	 * @param surename The surename parameter is the text to enter in the surename textbox while saving booking
	 * @param bookingPrice The bookingprice parameter is the text to enter in the price textbox while saving booking
	 * @param deposit The deposit parameter is to set value in deposit dropdown while saving booking
	 * @param checkinDate The firstname parameter is the text to enter in the firstname textbox while saving booking
	 * @param checkoutDate The checkoutDateis a parameter to set date in textbox datepicker while saving booking
	 */
	public void enterDetails(String firstname, String surename, String bookingPrice, String deposit, String checkinDate, String checkoutDate) {
		first_name.sendKeys(firstname);
		sure_name.sendKeys(surename);
		price.sendKeys(bookingPrice);
		enterDeposit(deposit);
		checkInDate(checkinDate);
		checkOutDate(checkoutDate);
	}

	/**
	 *
	 * @param textDisplayed This is the text by which we get the entire saved row assert the row after saving the details
	 */
	public void checkDetailsDisplayed(String textDisplayed){
		driver.navigate().refresh();
		WebElement element = driver.findElement(By.xpath("//p[text()='"+ textDisplayed +"']/ancestor::div[@class='row']"));
		Assert.assertTrue(element.isDisplayed());
	}

	/**
	 *
	 * @param textDisplayed this is the text that has to be verified that it is not present on the page
	 */
	public void checkDetailsAreDeleted(String textDisplayed){
		List<WebElement> elements = driver.findElements(By.xpath("//div[@id='bookings']/div[@class='row']/div[1]/p"));
		for (WebElement element : elements) {
			String firstName = element.getText();
			Assert.assertFalse(firstName==textDisplayed);
			break;
		}
	}

	/**
	 *
	 * @param textDisplayed this is the text used yto get the delete button for each row
	 */
	public void clickDeleteButton(String textDisplayed){
		WebElement element = driver.findElement(By.xpath("//p[text()='"+ textDisplayed +"']/ancestor::div[@class='row']/div[7]"));
		element.click();
	}



}
		

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	