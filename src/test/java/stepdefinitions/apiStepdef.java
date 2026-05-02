//package stepdefinitions;
//
//import utils.apiUtils;
//import io.cucumber.java.en.*;
//import io.restassured.response.Response;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class apiStepdef {
//
//    Response response;
//    String endpoint;
//    String userId;
//
//    @Given("user set GET API endpoint")
//    public void setGetEndpoint() {
//        endpoint = "/user";
//    }
//
//    @When("user send GET request")
//    public void sendGetRequest() {
//        response = apiUtils.get(endpoint);
//        response.prettyPrint();
//    }
//
//    @Given("user set POST API endpoint")
//    public void setPostEndpoint() {
//        endpoint = "/user/create";
//    }
//
//    @When("user send POST request with valid body")
//    public void sendPostRequest() {
//        response = apiUtils.post(endpoint, apiUtils.createUserBody());
//        response.prettyPrint();
//        userId = response.jsonPath().getString("id");
//    }
//
//    @Given("user set PUT API endpoint")
//    public void setPutEndpoint() {
//        Response createResponse = apiUtils.post("/user/create", apiUtils.createUserBody());
//        userId = createResponse.jsonPath().getString("id");
//
//        endpoint = "/user/" + userId;
//    }
//
//    @When("user send PUT request with valid body")
//    public void sendPutRequest() {
//        response = apiUtils.put(endpoint, apiUtils.createUserBody());
//        response.prettyPrint();
//    }
//
//    @Given("user set DELETE API endpoint")
//    public void setDeleteEndpoint() {
//        Response createResponse = apiUtils.post("/user/create", apiUtils.createUserBody());
//        userId = createResponse.jsonPath().getString("id");
//
//        endpoint = "/user/" + userId;
//    }
//
//    @When("user send DELETE request")
//    public void sendDeleteRequest() {
//        response = apiUtils.delete(endpoint);
//        response.prettyPrint();
//    }
//
//    @Then("user get response status code {int}")
//    public void validateStatusCode(int statusCode) {
//        assertEquals(statusCode, response.getStatusCode());
//    }
//}


package stepdefinitions;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import utils.apiUtils;

import static org.junit.jupiter.api.Assertions.*;

public class apiStepdef {

    Response response;
    String endpoint;
    String userId;

    // ================= GET USER =================

    @Given("user set GET API endpoint")
    public void setGetEndpoint() {
        endpoint = "/user";
    }

    @When("user send GET request")
    public void sendGetRequest() {
        response = apiUtils.get(endpoint);
        response.prettyPrint();
    }

    // NEGATIVE GET BY INVALID ID
    @Given("user set GET API endpoint with invalid user id")
    public void setGetInvalidId() {
        endpoint = "/user/invalid-id-12345";
    }

    @When("user send GET request by invalid id")
    public void sendGetInvalidRequest() {
        response = apiUtils.get(endpoint);
        response.prettyPrint();
    }

    // ================= CREATE USER =================

    @Given("user set POST API endpoint")
    public void setPostEndpoint() {
        endpoint = "/user/create";
    }

    @When("user send POST request with valid body")
    public void sendPostRequest() {
        response = apiUtils.post(endpoint, apiUtils.createUserBody());
        response.prettyPrint();
        userId = response.jsonPath().getString("id");
    }

    // NEGATIVE CREATE USER
    @When("user send POST request with empty body")
    public void sendPostEmptyBody() {
        response = apiUtils.post(endpoint, "{}");
        response.prettyPrint();
    }

    // ================= UPDATE USER =================

    @Given("user set PUT API endpoint")
    public void setPutEndpoint() {
        Response createResponse = apiUtils.post("/user/create", apiUtils.createUserBody());
        userId = createResponse.jsonPath().getString("id");

        endpoint = "/user/" + userId;
    }

    @When("user send PUT request with valid body")
    public void sendPutRequest() {
        response = apiUtils.put(endpoint, apiUtils.createUserBody());
        response.prettyPrint();
    }

    // ================= DELETE USER =================

    @Given("user set DELETE API endpoint")
    public void setDeleteEndpoint() {
        Response createResponse = apiUtils.post("/user/create", apiUtils.createUserBody());
        userId = createResponse.jsonPath().getString("id");

        endpoint = "/user/" + userId;
    }

    @When("user send DELETE request")
    public void sendDeleteRequest() {
        response = apiUtils.delete(endpoint);
        response.prettyPrint();
    }

    // ================= VALIDATION =================

    @Then("user get response status code {int}")
    public void validateStatusCode(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }
}