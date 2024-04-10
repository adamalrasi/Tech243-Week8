package com.sparta.aa.simpleclient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;

public class bulkRequest {
    public static void main(String[] args) {

        HttpClient httpClient = HttpClient.newHttpClient();

//    private String jsonData = "{""postcodes" : ["PR3 0SG", "M45 6GN", "EX165BL"]}"
//        String multiplePostCodes = "{ \"postcodes\" : [\"PR3 0SG\", \"M45 6GN\", \"EX165BL\"] }";
        String geoReversing = "{  \"geolocations\" : [ {  \"longitude\" : -3.15807731271522,  \"latitude\" : 51.4799900627036  },{  \"longitude\" : -1.12935802905177,  \"latitude\" : 50.7186356978817,  \"limit\" : 100,  \"radius\" : 500  } ]}";


        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://api.postcodes.io/postcodes"))
                .POST(HttpRequest.BodyPublishers.ofString(geoReversing, StandardCharsets.UTF_8))
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> httpResponse = null;

        try {
            httpResponse = httpClient.send(
                    httpRequest,
                    HttpResponse.BodyHandlers.ofString()
            );
            System.out.println(httpResponse);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(httpResponse.body());

        JSONParser jsonParser = new JSONParser();
        try {
            JSONObject jsonObject = (JSONObject)jsonParser.parse(httpResponse.body());
            System.out.println(jsonObject.get("status"));

//            System.out.println(jsonObject.get("result"));
            JSONObject resultObject = (JSONObject) jsonObject;
            System.out.println(resultObject.get("result"));


        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
