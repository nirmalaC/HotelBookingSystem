package stepDefinitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Given;
import helpers.UiHelper;
import org.openqa.selenium.support.PageFactory;
import pageobjects.bookingHomePage;

public class StepDefinitions {

    private bookingHomePage page = PageFactory.initElements(bookingHomePage.driver, bookingHomePage.class);

    private UiHelper uiHelper = new UiHelper();

    @Given("^I enter vaild details : (.*), (.*), (.*), (.*), (.*), (.*)$")
    public void iEnterVaildDetailsFirstnameSurenamePriceDeposit(String firstname, String surename, String bookingprice, String deposit, String checinDate, String checkoutDate ) {
        page.enterDetails(firstname,surename, bookingprice, deposit, checinDate, checkoutDate);
    }

    @When("^I click on the save button$")
    public void iClickOnTheSaveButton() throws Throwable {
        page.save_button.click();
    }

    @Then("^I should be able to save the booking : (.*), (.*), (.*), (.*), (.*), (.*)$")
    public void iShouldBeAbleToMake(String firstname, String surename, String bookingprice, String deposit, String checinDate, String checkoutDate) throws Throwable {
        page.checkDetailsIsDisplayed(firstname, surename, bookingprice, deposit,checinDate,checkoutDate );
        uiHelper.clickDeleteButton(firstname);
    }

    @Given("^vaild booking exists : (.*), (.*), (.*), (.*), (.*), (.*)$")
    public void vaildBookingExistsFirstnameSurename(String firstname, String surename, String bookingprice, String deposit, String checinDate, String checkoutDate) {
        page.checkDetailsIsDisplayed(firstname, surename, bookingprice, deposit,checinDate,checkoutDate );
    }

    @When("^I click on the delete button : (.*)$")
    public void iClickOnTheDeleteButton(String firstname){
        uiHelper.clickDeleteButton(firstname);
    }

    @Then("^the saved bookings should be deleted : (.*), (.*)$")
    public void theSavedBookingsShouldBeDeletedFirstnameSurename(String firstname, String surename) {
        page.checkDetailsAreNotDisplayed(firstname, surename);
    }
}