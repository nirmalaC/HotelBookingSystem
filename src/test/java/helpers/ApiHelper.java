package helpers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import gherkin.deps.com.google.gson.JsonParseException;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;
import static com.google.common.base.Preconditions.checkArgument;
import static io.restassured.RestAssured.with;


public class ApiHelper {

    public static Logger Log = Logger.getLogger(ApiHelper.class.getName());

    public String fs = File.separator;

    /**
     *
     * @param URL -- Parameter used to post the data.
     * @return -- Returns a validatableResponse which can be used by other metheds.
     * @throws IOException -- This exception happens when there is a failure during reading, writing and searching file or directory operations.
     * @throws JsonParseException -- Exception type for parsing problems, used when non-well-formed content (content that does not conform to JSON syntax as per specification) is encountered.
     */
    public ValidatableResponse postUserDetails(String URL) throws IOException, JsonParseException {

        String checkindate = resolveDateformat("sysdate+10");
        String checkoutdate = resolveDateformat("sysdate+15");
        Log.info("resolved checkindate ::: " + checkindate);

        // Jackson Object mapper to parse JSON
        ObjectMapper objectMapper = new ObjectMapper();

        String jsonPath = "src" + fs + "test" + fs + "resources" + fs + "jsonFiles" + fs + "Post.json";
        String path = Paths.get(jsonPath).toAbsolutePath().toString();

        //Jackson can read JSON into a JsonNode instance, and write a JsonNode out to JSON
        JsonNode obj = objectMapper.readTree(new File(path));

        // Update json values
        ((ObjectNode) obj.get("bookingdates")).put("checkin", checkindate);
        ((ObjectNode) obj.get("bookingdates")).put("checkout", checkoutdate);


        ValidatableResponse validatableResponse =
                with()
                        .baseUri(URL)
                        .contentType(ContentType.JSON)
                        .body(obj.toString())
                        .log()
                        .all()
                        .post()
                        .then()
                        .log()
                        .all()
                        .assertThat()
                        .statusCode(200);

        return validatableResponse;
    }

    /**
     *
     * @param URL -- Parameter used to post the data.
     * @param bookingId -- Parameter used to get a specific user details.
     * @return -- Rreturns a validatableResponse which can be used by other metheds.
     */
    public ValidatableResponse getUserDetails(String URL, String bookingId) {

        ValidatableResponse validatableResponse =
                with()
                        .baseUri(URL + "/" + bookingId)
                        .contentType(ContentType.JSON)
                        .get()
                        .then()
                        .assertThat()
                        .statusCode(200);
            return validatableResponse;
        }

    /**
     *
     * @param URL -- Parameter used to post the data.
     * @param bookingId -- Parameter used to get a specific user details.
     * @return -- eturns a validatableResponse which can be used by other metheds.
     */
    public ValidatableResponse deleteUserDetails(String URL, String bookingId) {

        ValidatableResponse validatableResponse =
                with()
                        .baseUri(URL + "/" + bookingId)
                        .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                        .contentType(ContentType.JSON)
                        .delete()
                        .then()
                        .assertThat()
                        .statusCode(201);
        return validatableResponse;
    }

    /**
     *
     * @param dateMacro -- Parameter used to resolve the dates.
     * @return -- Returns a validatableResponse which can be used by other metheds.
     */
    public static String resolveDateformat(final String dateMacro) {
        checkArgument(dateMacro.contains("sysdate"),
                "Only sysdate - today is supported, additions and subtractions. e.g: sysdate+1, sysdate, sysdate-2");

        final String extracted = dateMacro.trim().substring("sysdate".length(), dateMacro.length());
        int amount = extracted.length() == 0 ? 0 : Integer.parseInt(extracted);

        Log.info("Adding/Subtracting to sysdate " + amount);

        LocalDateTime localDateTime;
        if (Integer.signum(amount) == 0) {
            localDateTime = LocalDateTime.now();
        } else {
            if (Integer.signum(amount) == 1) {
                localDateTime = LocalDateTime.now().plusDays(amount);
            } else {
                localDateTime = LocalDateTime.now().minusDays(Math.abs(amount));
            }
        }

        return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDateTime);
    }

    public ValidatableResponse getRequest(String URL) throws IOException, ParseException {

        ValidatableResponse validatableResponse =
                with()
                        .baseUri(URL)
                        .contentType(ContentType.JSON)
                        .get()
                        .then()
                        .assertThat()
                        .statusCode(200);
        return validatableResponse;
    }
}




