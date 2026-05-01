package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class apiUtils {

    public static final String BASE_URL = "https://dummyapi.io/data/v1";
    public static final String APP_ID = "63a804408eb0cb069b57e43a";

    public static Response get(String endpoint) {
        return RestAssured
                .given()
                .header("app-id", APP_ID)
                .when()
                .get(BASE_URL + endpoint);
    }

    public static Response post(String endpoint, Object body) {
        return RestAssured
                .given()
                .header("app-id", APP_ID)
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post(BASE_URL + endpoint);
    }

    public static Response put(String endpoint, Object body) {
        return RestAssured
                .given()
                .header("app-id", APP_ID)
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .put(BASE_URL + endpoint);
    }

    public static Response delete(String endpoint) {
        return RestAssured
                .given()
                .header("app-id", APP_ID)
                .when()
                .delete(BASE_URL + endpoint);
    }

    public static Map<String, Object> createUserBody() {
        Map<String, Object> body = new HashMap<>();
        body.put("firstName", "Alvin");
        body.put("lastName", "QA");
        body.put("email", "alvin" + System.currentTimeMillis() + "@mail.com");
        body.put("title", "mr");
        body.put("picture", "https://randomuser.me/api/portraits/men/1.jpg");
        return body;
    }
}