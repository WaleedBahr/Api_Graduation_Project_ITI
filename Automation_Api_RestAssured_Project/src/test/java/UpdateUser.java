import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.HashMap;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class UpdateUser {
    RequestSpecification request;
    @BeforeTest
    public void openBrowser(){
        request=given()
                .baseUri("https://reqres.in")
                .log().all();
    }
    @Test
    public void PutUpdateUser(){
        HashMap<String,String> body = new HashMap<>();
        body.put("name","Waleed");
        body.put("job","Software Test Engineer");
        System.out.println(body);
        given()
                .spec(request)
                .body(body)
                .header("Content-Type","application/json")
        .when()
                .put("/api/users/2")
        .then()
                .statusCode(200)
                .time(lessThan(4000l))
                .body("name",equalTo("Waleed"))
                .body("job",equalTo("Software Test Engineer"));
        Response response =get("https://reqres.in/api/users/2");
        System.out.println("Response : "+response.asString());
        System.out.println("Status Code : "+response.getStatusCode());
        long responseTime = response.getTime();
        System.out.println("Response Time : "+responseTime);
        System.out.println("Body : "+response.getBody().asString());


    }
}
