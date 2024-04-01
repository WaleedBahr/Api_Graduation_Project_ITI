import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.HashMap;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class RegisterSuccessful {
    RequestSpecification request;
    @BeforeTest
    public void OPenBrowser(){
        request=given()
                .baseUri("https://reqres.in")
                .log().all();
    }
    @Test
    public void PostRegisterSuccessful(){
        HashMap<String,String> body = new HashMap<>();
        body.put( "email","eve.holt@reqres.in");
        body.put("password","pistol");
        System.out.println(body);
        given()
                .spec(request)
                .header("Content-Type","application/json")
                .header("Authorization","Bearer QpwL5tke4Pnpja7X4")
                .auth().oauth2("QpwL5tke4Pnpja7X4")
                .body(body)
        .when()
                .post("/api/register")
        .then()
                .statusCode(200)
                .time(lessThan(4000l))
                .body("id",equalTo (4))
                .body("token", equalTo ("QpwL5tke4Pnpja7X4"))
                .log().body();

    }
}
