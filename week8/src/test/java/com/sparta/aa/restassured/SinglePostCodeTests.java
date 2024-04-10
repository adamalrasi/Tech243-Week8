package com.sparta.aa.restassured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Map;
import org.junit.platform.engine.support.discovery.SelectorResolver;

import java.util.Arrays;
import java.util.stream.Collectors;


public class SinglePostCodeTests {

    private static Response response;

    @BeforeAll
    static void beforeAll(){
        response = RestAssured
                .given()
                .baseUri("https://api.postcodes.io")
                .basePath("/postcodes")
                .header("Accept", "text/json")
                .when()
                .get("/EC2Y5AS")
                .thenReturn();
    }

    @Test
    @DisplayName("Status code 200 returned")

    void testStatusCode200() {
        RestAssured
                .given()
                    .baseUri("https://api.postcodes.io")
                    .basePath("/postcodes")
                    .header("Accept", "text/json")
                .when()
                    .get("/EC2Y5AS")
                .then()
//                    .log().all()
                    .assertThat()
                    .statusCode(200);

    }

    @Test
    @DisplayName("Status code 200 returned")
    void testStatusCode200B() {
        MatcherAssert.assertThat(response.statusCode(), Matchers.is(200));
    }

    @Test
    @DisplayName("Server name is CloudFlare")
    void testServerName(){
        MatcherAssert.assertThat(response.header("Server"), Matchers.is("cloudflare"));
    }

    @Test
    @DisplayName("Correct postcode returned in response")
    void testCorrectPostcodeReturnedInResponse() {
        MatcherAssert.assertThat(
                response.jsonPath().getString("result.postcode"),
                Matchers.is("EC2Y 5AS"));
    }

    @Test
    @DisplayName("Correct primary care trust")
    void testCorrectPrimaryCareTrust() {
        MatcherAssert.assertThat(
                response.jsonPath().getString("result.primary_care_trust"),
                Matchers.is("City and Hackney Teaching"));
    }

    @Test
    @DisplayName("Testing the total number of codes returned is 14")
    void testingTheTotalNumberOfCodesReturnedIs14() {
        MatcherAssert.assertThat(response.jsonPath().getMap("result.codes").size(),Matchers.is(14));
    }






}
