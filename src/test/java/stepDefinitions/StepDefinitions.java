package stepDefinitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Given;
import org.openqa.selenium.support.PageFactory;
import pageobjects.BookingHomePage;

public class StepDefinitions {

    BookingHomePage page = PageFactory.initElements(BookingHomePage.driver, BookingHomePage.class);

    /**
     *
     * @param firstname The firstname parameter is the text to enter in the firstname textbox while saving booking
     * @param surename The surename parameter is the text to enter in the surename textbox while saving booking
     * @param bookingprice The bookingprice parameter is the text to enter in the price textbox while saving booking
     * @param deposit The deposit parameter is to set value in deposit dropdown while saving booking
     * @param checinDate The firstname parameter is the text to enter in the firstname textbox while saving booking
     * @param checkoutDate The checkoutDateis a parameter to set date in textbox datepicker while saving booking
     */
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
    }

    @Given("^vaild booking exists : (.*), (.*), (.*), (.*), (.*), (.*)$")
    public void vaildBookingExistsFirstnameSurename(String firstname, String surename, String bookingprice, String deposit, String checinDate, String checkoutDate) {
        page.checkDetailsIsDisplayed(firstname, surename, bookingprice, deposit,checinDate,checkoutDate );
    }

    @When("^I click on the delete button : (.*)$")
    public void iClickOnTheDeleteButton(String firstname){
        page.clickDeleteButton(firstname);
    }

    @Then("^the saved bookings should be deleted : (.*), (.*)$")
    public void theSavedBookingsShouldBeDeletedFirstnameSurename(String firstname, String surename) {
        page.checkDetailsAreNotDeleted(firstname, surename);
    }
}