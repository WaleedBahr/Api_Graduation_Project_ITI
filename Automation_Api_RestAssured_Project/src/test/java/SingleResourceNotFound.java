import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class SingleResourceNotFound {
    RequestSpecification request;
    @BeforeTest
    public void OPenBrowser(){
        request=given()
                .baseUri("https://reqres.in")
                .log().all();
    }
    @Test
    public void GetSingleResourceNotFound(){
        Response response =   get("https://reqres.in/api/unknown/23");
        System.out.println("Response : "+response.asString());
        System.out.println("Status Code : "+response.getStatusCode());
        long responseTime = response.getTime();
        System.out.println("Response Time : "+responseTime);
        System.out.println("Body : "+response.getBody().asString());
        given()
                .spec(request)
        .when()
                .get("/api/unknown/23")
        .then()
                .log().all()
                .statusCode(404)
                .time(lessThan(3000l));
    }
}
