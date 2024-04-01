import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.HashMap;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class CreateUser {
    RequestSpecification request;
    @BeforeTest
    public void openBrowser(){
        request=given()
                .baseUri("https://reqres.in")
                .log().all();
    }
    @Test
    public void PostCreateUser(){
        HashMap<String,String> body = new HashMap<>();
        body.put("name","morpheus");
        body.put("job","leader");
        System.out.println(body);
        given()
                .spec(request)
                .header("Content-Type","application/json")
                .body(body)

        .when()
                .post("/api/users")
        .then()
                .statusCode(201)
                .time(lessThan(4000l))
                .body("name",equalTo ("morpheus"))
                .body("job", equalTo ("leader"));
        Response response =get("https://reqres.in/api/users");
        System.out.println("Response : "+response.asString());
        System.out.println("Status Code : "+response.getStatusCode());
        long responseTime = response.getTime();
        System.out.println("Response Time : "+responseTime);
        System.out.println("Body : "+response.getBody().asString());


    }
}
