import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class SingleResource {
    RequestSpecification request;
    @BeforeTest
    public void OPenBrowser(){
        request=given()
                .baseUri("https://reqres.in")
                .log().all();
    }
    @Test
    public void GetSingleResource(){
        Response response =   get("https://reqres.in/api/unknown/2");
        System.out.println("Response : "+response.asString());
        System.out.println("Status Code : "+response.getStatusCode());
        long responseTime = response.getTime();
        System.out.println("Response Time : "+responseTime);
        System.out.println("Body : "+response.getBody().asString());
        given()
                .spec(request)
        .when()
                .get("/api/unknown/2")
        .then()
                .log().all()
                .statusCode(200)
                .time(lessThan(3000l))
                .body("data.id", equalTo(2))
                .body("data.name", equalTo("fuchsia rose"))
                .body("data.year", equalTo(2001))
                .body("data.color", equalTo("#C74375"))
                .body("data.pantone_value", equalTo("17-2031"))
                .body("support.url", equalTo("https://reqres.in/#support-heading"))
                .body("support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));
    }

    }

