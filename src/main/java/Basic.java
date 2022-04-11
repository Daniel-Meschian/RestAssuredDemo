import files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class Basic {

    public static void main(String[] args) {
        Payload payload = new Payload();

        RestAssured.baseURI = "http://bb-training.test.devsmm.com/api";

        //Register a new user
        given().log().all().header("Accept", "application/json")
                .contentType("application/json")
                .body(payload.registerNewUser())
                .when().post("/register")
                .then()
                .assertThat().statusCode(201).body("data.user.first_name", equalTo("Filomena"))
                .header("server", "Apache/2.4.6 (CentOS) PHP/7.2.17");

        //Login with generated credentials
        String response  = given().log().all().header("Accept", "application/json")
                .contentType("multipart/form-data")
                .multiPart("email", payload.getEmail())
                .multiPart("password", payload.getPassword())
                .when().post("/auth/login")
                .then()
                .assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath jsonPath = new JsonPath(response); //for parsing json
        String token = jsonPath.getString("data.access_token");

        //Get specific information
        given().log().all().headers("Authorization","Bearer " + token,"Accept", "application/json")
                .when().get("/users/12616")
                .then()
                .assertThat().statusCode(200);

        //Logout
        given().log().all().headers("Authorization","Bearer " + token,"Accept", "application/json")
                .when().post("/auth/logout")
                .then()
                .assertThat().log().all().statusCode(200);
    }
}
