package stepDefinitions;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helpers.ApiHelper;
import io.restassured.response.ValidatableResponse;
import org.json.simple.parser.ParseException;
import org.junit.Assert;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static helpers.ApiHelper.resolveDateformat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ApiStepDefinitions {

    public static Logger Log = Logger.getLogger(ApiStepDefinitions.class.getName());
    public String URL = "http://hotel-test.equalexperts.io/booking";
    private ApiHelper steps = new ApiHelper();
    public String bookingId;

    @Given("^I have POST service api endpoint$")
    public void iHavePOSTServiceApiEndpoint() {
        Log.info("api POST endpoint ::: " + URL);
    }

    @When("^I post my booking details$")
    public void iPostMyBookingDetails() throws IOException {
        bookingId = steps.postUserDetails(URL).extract().jsonPath().getString("bookingid");
        Log.info("Booking ID ::: " + bookingId);
    }

    @Then("^I should be able to create my bookings$")
    public void iShouldBeAbleToCreateMyBookings(DataTable table) {

        ValidatableResponse response = steps.getUserDetails(URL, bookingId);

        Log.info("FIRSTNAME ::: " + response.extract().response().jsonPath().getString("firstname"));
        for (Map<String, String> data : table.asMaps(String.class, String.class)) {
            Log.info("Entered into loop");
            assertThat("Check the value for Firstname  :: ", response.extract().response().jsonPath().getString("firstname"), is(equalTo(data.get("firstname"))));
            assertThat("Check the value for Lastname  :: ", response.extract().response().jsonPath().getString("lastname"), is(equalTo(data.get("surename"))));
            assertThat("Check the value for Price  :: ", response.extract().response().jsonPath().getString("totalprice"), is(equalTo(data.get("price"))));
            assertThat("Check the value for Deposit  :: ", response.extract().response().jsonPath().getString("depositpaid"), is(equalTo((data.get("deposit")))));
            assertThat("Check the value for CheckinDate  :: ", response.extract().response().jsonPath().getString("bookingdates.checkin"), is(equalTo(resolveDateformat(data.get("checkindate")))));
            assertThat("Check the value for CheckoutDate  :: ", response.extract().response().jsonPath().getString("bookingdates.checkout"), is(equalTo(resolveDateformat(data.get("checkoutdate")))));
        }
        steps.deleteUserDetails(URL, bookingId);
    }


    @Given("^I have DELETE service api endpoint$")
    public void iHaveDELETEServiceApiEndpoint() throws IOException {
        Log.info("api POST endpoint ::: " + URL + "/" + bookingId);
        bookingId = steps.postUserDetails(URL).extract().jsonPath().getString("bookingid");
        Log.info("Booking ID ::: " + bookingId);
    }

    @When("^I delete my booking details$")
    public void iDeleteMyBookingDetails() {
        steps.deleteUserDetails(URL, bookingId);
    }

    @Then("^the saved bookings should be deleted$")
    public void theSavedBookingsShouldBeDeleted() throws IOException, ParseException {
        ValidatableResponse response = steps.getRequest(URL);
        List<Integer> bookingid = response.extract().response().jsonPath().getList("bookingid");
        Log.info("List of booking id :: " + bookingid);
        for (Integer id : bookingid) {
            Log.info("id got :: " + id);
            Assert.assertNotEquals(id.toString(), bookingId);
        }
    }

}
