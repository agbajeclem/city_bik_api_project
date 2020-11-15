package model;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.api.rest.api.restassuredhelper.BaseClass;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TestGet extends BaseClass {

      @Test
    public void testStatusCode() throws URISyntaxException {

        /*
         * Given Accept the response in JSON format When I perform the GET
         * request Then Status code 200 OK should be returned *
         */

                given()
                .accept(ContentType.JSON)
                .when()
                .get(new URI("/"))
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }


      @Test
    public void testGetWithIdWithHeader() throws URISyntaxException {
        /**
         * Given Accept the content in JSON format When I perform the GET
         * request with id visa-frankfurt
         * Then Status code 200 OK should be returned
         * */

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Accept", "application/json");

        String body = given()
                .headers(headers)
                .when()
                .get(new URI("/visa-frankfurt"))
                .thenReturn()
                .body()
                .asString();
        // System.out.println(body);
    }

    @Test
    public void testGetWithNonExistId() throws URISyntaxException {
        /**
         * Given Accept the content in JSON format When I perform the GET
         * request with clement Then Status code 404 Not Found should be returned
         * */

        System.out.println(baseURI + basePath);
        given().accept(ContentType.JSON)
                .when()
                .get(new URI("/clement"))
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

      @Test
    public void testQueryParamWithIdNameHref() {

        /**
         * Given Accept the content in JSON format
         * And query fields are id,name and href
         * When I perform the GET Request
         * Then Status code 200 OK should be returned
         *
         * **/

        given()
                .accept(ContentType.JSON)
                .param("fields", "id", "name", "href")
                .when()
                .get("/")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

      @Test
    public void testFrankfurtIsInGermany() {
        /**
         * Given Accept the content in JSON format
         * When I perform the GET method with id as visa-frankfurt
         * Then The response should have City as Frankfurt
         * And The Country should have ISO Code as DE
         * And corresponded latitude and longitude should be returned/populated
         *
         * **/

        given().accept(ContentType.JSON)
                .when()
                .get("/visa-frankfurt")
                .then()
                .assertThat()
                .body("network.location.city", equalTo("Frankfurt"),
                        "network.location.country", equalTo("DE"),
                        "network.location.latitude", equalTo(50.1072f),
                        "network.location.longitude", equalTo(8.66375f))
                .and()
                .assertThat()
                .body("network.company", hasItem("Nextbike GmbH"));

    }

    @Test
    public void testContentUsingObjectMapper() throws IOException {

        /**
         * Given Accept the content in Json format
         * When I perform the GET method with id as visa-frankfurt
         * Then The response should have longitude and latitude
         * And visa-frankfurt has 101 Stations
         * And The network should have name,stations etc
         * **/

        String s = given()
                .accept(ContentType.JSON)
                .when()
                .get("/visa-frankfurt")
                .thenReturn()
                .asString();

        ResponseBody responseBody = new ObjectMapper().readerFor(ResponseBody.class).readValue(s);
        Assert.assertEquals("visa-frankfurt", responseBody.getNetwork().getId());
        Assert.assertEquals("Frankfurt", responseBody.getNetwork().getLocation().getCity());
        Assert.assertEquals("DE", responseBody.getNetwork().getLocation().getCountry());
        Assert.assertNotNull(responseBody.getNetwork().getLocation().getLatitude());
        Assert.assertNotNull(responseBody.getNetwork().getLocation().getLongitude());
        Assert.assertNotNull(responseBody.getNetwork().getName());
        Assert.assertNotNull(responseBody.getNetwork().getStations());
        Assert.assertThat(responseBody.getNetwork().getStations(),hasSize(101));

    }
}