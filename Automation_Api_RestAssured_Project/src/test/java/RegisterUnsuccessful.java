import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.File;
import java.util.HashMap;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class RegisterUnsuccessful {
    RequestSpecification request;
    @BeforeTest
    public void openBrowser(){
        request=given()
                .baseUri("https://reqres.in")
                .log().all();
    }
    @Test
    public void PostRegisterUnsuccessful(){
        File body = new File("src/test/resources/data.json");
        given()
                .spec(request)
                .header("Content-Type","application/json")
                .body(body)
        .when()
                .post("/api/register")
        .then()
                .statusCode(400)
                .time(lessThan(3000l))
                .body("error", equalTo ("Missing password"))
                .log().body();

    }
}
