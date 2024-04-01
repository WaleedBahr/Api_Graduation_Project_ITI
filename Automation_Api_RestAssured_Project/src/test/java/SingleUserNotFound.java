import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class SingleUserNotFound {
    RequestSpecification request;
    @BeforeTest
    public void openBrowser(){
        request=given()
                .baseUri("https://reqres.in")
                .log().all();
    }
    @Test
    public void GetSINGLEUSERNOTFOUND(){
        Response response =   get("https://reqres.in/api/users/23");
        System.out.println("Response : "+response.asString());
        System.out.println("Status Code : "+response.getStatusCode());
        long responseTime = response.getTime();
        System.out.println("Response Time : "+responseTime);
        System.out.println("Body : "+response.getBody().asString());
        given()
                .spec(request)
                .when().get("/api/users/23")
                .then().log().all()
                .statusCode(404)
                .time(lessThan(3000l));
        Assert.assertTrue(responseTime<3000);


    }

}
