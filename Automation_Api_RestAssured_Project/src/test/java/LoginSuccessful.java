import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.HashMap;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class LoginSuccessful {
    RequestSpecification request;
    @BeforeTest
    public void openBrowser(){
        request=given()
                .baseUri("https://reqres.in")
                .log().all();
    }
    @Test
    public void PostLoginSuccessful(){
        given()
                .spec(request)
                .header("Content-Type","application/json")
                .header("Authorization","Bearer QpwL5tke4Pnpja7X4")
                .auth().oauth2("QpwL5tke4Pnpja7X4")
                .body("{\"email\": \"eve.holt@reqres.in\", " +
                        "\"password\": \"cityslicka\"}")

        .when()
                .post("/api/login")
        .then()
                .statusCode(200)
                .time(lessThan(4000l))
                .body("token", equalTo ("QpwL5tke4Pnpja7X4"))
                .log().body();

    }
}
