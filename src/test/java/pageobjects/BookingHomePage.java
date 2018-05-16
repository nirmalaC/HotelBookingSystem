package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import stepDefinitions.Hooks;

import java.util.List;


public class BookingHomePage extends BaseClass{
	public static WebDriver driver;

//	@FindBy(id = "firstname")
//	public static WebElement first_name;

//	@FindBy(how= How.ID, using="firstname")
//	public static WebElement first_name;

	@FindBy(how= How.CSS, using="input[id=firstname]")
	public static WebElement first_name;
	
	@FindBy(how= How.ID, using="lastname")
	public static WebElement sure_name;
	
	@FindBy(how= How.ID, using="totalprice")
	public static WebElement price;

	@FindBy(how= How.ID, using="depositpaid")
	public static List<WebElement> deposit;

	@FindBy(how= How.ID, using="checkin")
	public static WebElement check_in;

	@FindBy(how= How.ID, using="checkout")
	public static WebElement check_out;

	@FindBy(how= How.CSS, using="input[onclick='createBooking()'] ")
	public static WebElement save_button;

	@FindBy(how= How.CSS, using="input[value='Delete']")
	public static WebElement delete_button;

	public BookingHomePage(WebDriver driver){
//		this.driver = Hooks.driver;
		super(Hooks.driver);
		PageFactory.initElements(driver, this);
	}

	public static void enterFirstName(String firstname, String surename, String price, Boolean deposit) throws InterruptedException {
//		System.out.println(first_name);
		first_name.sendKeys(firstname);
//		driver.findElement(By.cssSelector("input[id=firstname]")).sendKeys(surename);
//		driver.findElement(By.id("lastname")).sendKeys(surename);
//		driver.findElement(By.id("totalprice")).sendKeys(price);
		Thread.sleep(5000);
	}

}
		

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	