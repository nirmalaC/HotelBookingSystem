package pageobjects;

import org.openqa.selenium.WebDriver;

public abstract class BaseClass {
	public static WebDriver driver;

	public  BaseClass(WebDriver driver){
		BaseClass.driver = driver;
	}

	public WebDriver getDriver() {
		return this.driver;
	}

}
