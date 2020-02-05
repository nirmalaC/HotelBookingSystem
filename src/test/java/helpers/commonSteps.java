package helpers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import gherkin.deps.com.google.gson.JsonParseException;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

import static com.google.common.base.Preconditions.checkArgument;
import static io.restassured.RestAssured.with;


public class commonSteps {

    public static Logger Log = Logger.getLogger(commonSteps.class.getName());

    private final String UTF8_BOM = new String("\uFEFF".getBytes(StandardCharsets.UTF_8));

    public ValidatableResponse postUserDetails(String URL) throws IOException, ParseException, JsonParseException {

        String checkindate = resolveDateformat("sysdate+10");
        String checkoutdate = resolveDateformat("sysdate+15");
        Log.info("resolved checkindate ::: " + checkindate);


        // Jackson Object mapper can parse JSON
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode obj = objectMapper.readTree(new File("./src/test/resources/JsonFiles/Post.json"));


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


    public ValidatableResponse getUserDetails(String URL, String bookingId) throws IOException, ParseException {


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

    public ValidatableResponse deleteUserDetails(String URL, String bookingId) throws IOException, ParseException {


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




