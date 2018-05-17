package pageobjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import stepDefinitions.Hooks;

import java.util.List;


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
//
//	@FindBy(xpath ="//div[@id='bookings']/div[@class='row']/div/p[contains(text(),'"+ textDisplayed +"')]")
//	public static WebElement text_displayed;





	public void enterDeposit(String bookingDeposit){
		Select dropDown = new Select(deposit);
		dropDown.selectByVisibleText(bookingDeposit);
	}

	public void checkInDate(String checkinDate) throws InterruptedException {
		check_in.click();
		List<WebElement> rows = datepicker_table.findElements(By.tagName("tr"));
		List<WebElement> columns = datepicker_table.findElements(By.tagName("td"));

			for(WebElement cell : columns) {

			if(cell.getText().equals(checkinDate)){
				cell.findElement(By.linkText(checkinDate)).click();
				break;
			}}
		Thread.sleep(1000);
	}

	public void checkOutDate(String checkoutDate) throws InterruptedException {
       check_out.click();
		List<WebElement> rows = datepicker_table.findElements(By.tagName("tr"));
		List<WebElement> columns = datepicker_table.findElements(By.tagName("td"));

		for(WebElement cell : columns) {

			if(cell.getText().equals(checkoutDate)){
				cell.findElement(By.linkText(checkoutDate)).click();
				break;
			}}
		Thread.sleep(1000);
	}

	public void enterDetails(String firstname, String surename, String bookingPrice, String deposit, String checkinDate, String checkoutDate) throws InterruptedException {
		first_name.sendKeys(firstname);
		Thread.sleep(1000);
		sure_name.sendKeys(surename);
		Thread.sleep(1000);
		price.sendKeys(bookingPrice);
		Thread.sleep(1000);
		enterDeposit(deposit);
		Thread.sleep(1000);
		checkInDate(checkinDate);
		Thread.sleep(1000);
		checkOutDate(checkoutDate);
		Thread.sleep(1000);
	}

	public void checkDetailsDisplayed(String textDisplayed){
		WebElement element = driver.findElement(By.xpath("//p[text()='"+ textDisplayed +"']"));
		Assert.assertTrue(element.isDisplayed());
	}

	public void checkDetailsAreDeleted(String textDisplayed){
		WebElement element = driver.findElement(By.xpath("//p[text()='"+ textDisplayed +"']"));
		Assert.assertFalse(element.isDisplayed());
	}

}
		

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	