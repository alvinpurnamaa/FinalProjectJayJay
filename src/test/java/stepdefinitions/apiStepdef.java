package stepdefinitions;

import utils.apiUtils;
import io.cucumber.java.en.*;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.*;

public class apiStepdef {

    Response response;
    String endpoint;
    String userId;

    @Given("user set GET API endpoint")
    public void setGetEndpoint() {
        endpoint = "/user";
    }

    @When("user send GET request")
    public void sendGetRequest() {
        response = apiUtils.get(endpoint);
        response.prettyPrint();
    }

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

    @Then("user get response status code {int}")
    public void validateStatusCode(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }
}