package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import helpers.UiHelper;
import stepDefinitions.Hooks;

import java.util.logging.Logger;

import static helpers.UiHelper.datesDisplayed;


public class bookingHomePage extends Hooks{

	private UiHelper uiHelper = new UiHelper();

	public static Logger log = Logger.getLogger(String.valueOf(bookingHomePage.class));

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
		uiHelper.checkInDate(checkinDate);
		uiHelper.checkOutDate(checkoutDate);
	}


	/**
	 *
	 * @param firstname The firstname parameter is the text to enter that needs to be verified
	 * @param surename The surename parameter is the text to enter that needs to be verified
	 * @param bookingPrice The bookingprice parameter is the text to enter that needs to be verified
	 * @param deposit The deposit parameter is to text to enter that needs to be verified
	 * @param checkinDate The firstname parameter is the text to enter that needs to be verified
	 * @param checkoutDate The checkoutDateis a parameter is the text to enter that needs to be verified
	 */
	public void checkDetailsIsDisplayed(String firstname, String surename, String bookingPrice, String deposit, String checkinDate, String checkoutDate){
		uiHelper.clicksavedDetailsAreDisplayed(firstname, 1);
		uiHelper.clicksavedDetailsAreDisplayed(surename, 2);
		uiHelper.clicksavedDetailsAreDisplayed(bookingPrice, 3);
		uiHelper.clicksavedDetailsAreDisplayed(deposit, 4);
		clicksavedCheckInDateIsDisplayed(checkinDate, 5);
		clicksavedCheckOutDateIsDisplayed(checkoutDate, 6);

	}


	/**
	 *
	 * @param textValue Text value ued to verify if the text is displayed.
	 * @param divIndex This is the value used in the xpath for getting the webelements
	 */
	public void clicksavedCheckInDateIsDisplayed(String textValue, int divIndex){
		datesDisplayed(textValue, divIndex);
		log.info("CheckIn date is displayed");
	}

	/**
	 *
	 * @param textValue Text value ued to verify if the text is displayed.
	 * @param divIndex This is the value used in the xpath for getting the webelements
	 */
	public void clicksavedCheckOutDateIsDisplayed(String textValue, int divIndex){
		datesDisplayed(textValue, divIndex);
		log.info("CheckOut date is displayed");
	}



	/**
	 *
	 * @param firstname The firstname parameter is used to verify that the deleted row is not displayed.
	 * @param surename The surename parameter is used to verify that the deleted row is not displayed.
	 */
	public void checkDetailsAreNotDisplayed(String firstname, String surename){
		driver.navigate().refresh();
		uiHelper.checkFirstNameIsNotDisplayed(firstname, 1);
		uiHelper.checkFirstNameIsNotDisplayed(surename, 2);
	}

}
		

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	