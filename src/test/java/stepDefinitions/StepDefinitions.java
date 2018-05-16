package stepDefinitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static pageobjects.BookingHomePage.enterFirstName;


public class StepDefinitions {
    public WebDriver driver;

    public StepDefinitions()
    {
    	driver = Hooks.driver;
    }

    @Given("^I enter vaild details : (.*), (.*), (.*), (.*)$")
    public void iEnterVaildDetailsFirstnameSurenamePriceDeposit(String firstname, String surename, String bookingprice, Boolean deposit ) throws Throwable {
//        driver.findElement(By.cssSelector("input[id=firstname]")).sendKeys(surename);

        enterFirstName(firstname,surename, bookingprice, deposit);
    }

    @When("^I click on the save button$")
    public void iClickOnTheSaveButton() throws Throwable {

    }

    @Then("^I should be able to make$")
    public void iShouldBeAbleToMake() throws Throwable {

    }
}