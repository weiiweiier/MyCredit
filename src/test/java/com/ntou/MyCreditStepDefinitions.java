package com.ntou;

import com.ntou.tool.Common;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.junit.Assert.assertEquals;

public class MyCreditStepDefinitions {
    private Response response;

    @Given("the base API URL is {string}")
    public void setBaseURI(String baseURI) {
        RestAssured.baseURI = baseURI;
    }

    @When("I send a POST request to {string}")
    public void sendPostRequest(String endpoint) {
        String requestBody = "";
        switch(endpoint){
            case "/Query":
                System.out.println(Common.START_B + "Query");
                requestBody = "{\"cid\": \"A123456789\", \"cardNum\": \"1234567891234567\"}";
                break;
            case "/Insert":
                System.out.println(Common.START_B + "Insert");
                requestBody = "{\n" +
                        "    \"uuid\":\"77777778-9dad-11d1-80b4-00c04fd430c8\",\n" +
                        "    \"buyChannel\": \"02\",\n" +
                        "    \"buyDate\": \"2024/03/13 14:31:00.000\",\n" +
                        "    \"reqPaymentDate\": \"2024/03/13 14:31:00.080\",\n" +
                        "    \"shopId\": \"77527419\",\n" +
                        "    \"customerId\": \"A123456789\",\n" +
                        "    \"buyCurrency\": \"00\",\n" +
                        "    \"buyAmount\": \"10000\",\n" +
                        "    \"disputedFlag\": \"00\",\n" +
                        "    \"status\": \"00\",\n" +
                        "    \"actuallyDate\": \"2024/03/13 14:31:00.700\",\n" +
                        "    \"remark\": \"\",\n" +
                        "    \"issuingBank\": \"BKTWTWTP\",\n" +
                        "    \"cardNum\": \"1234567891234567\",\n" +
                        "    \"securityCode\": \"886\"\n" +
                        "}";
                break;
            case "/Modify":
                System.out.println(Common.START_B + "Modify");
                requestBody = "{\n" +
                        "    \"uuid\": \"88888888-9dad-11d1-80b4-00c04fd430c8\",\n" +
                        "    \"disputedFlag\": \"99\",\n" +
                        "    \"status\": \"99\",\n" +
                        "    \"actuallyDate\": \"2024/03/13 18:40:00.000\"\n" +
                        "}";
                break;
        }
        response = RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .post(endpoint);
    }

    @Then("the response code should be {int}")
    public void verifyResponseCode(int expectedStatusCode) {
        assertEquals(expectedStatusCode, response.getStatusCode());
        System.out.println("res："+response.getBody().asString());
        System.out.println(Common.END_B + "\n");
    }
}
