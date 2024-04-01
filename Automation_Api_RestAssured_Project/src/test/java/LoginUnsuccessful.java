import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.HashMap;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class LoginUnsuccessful {
    RequestSpecification request;
    @BeforeTest
    public void openBrowser(){
        request=given()
                .baseUri("https://reqres.in")
                .log().all();
    }
    @Test
    public void PostLoginUnsuccessful(){
        given()
                .spec(request)
                .header("Content-Type","application/json")
                .header("Authorization","Bearer QpwL5tke4Pnpja7X4")
                .auth().oauth2("QpwL5tke4Pnpja7X4")
                .body("{\n" +
                "    \"email\": \"peter@klaven\"\n" +
                "}")
        .when()
                .post("/api/login")
        .then()
                .statusCode(400)
                .time(lessThan(3000l))
                .body("error", equalTo ("Missing password"))
                .log().body();

    }

}
