import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class SingleUser {
    RequestSpecification request;
    @BeforeTest
    public void OPenBrowser(){
        request=given()
                .baseUri("https://reqres.in")
                .log().all();
    }
    @Test
    public void GetSingleUser(){
        Response response =   get("https://reqres.in/api/users/2");
        System.out.println("Response : "+response.asString());
        System.out.println("Status Code : "+response.getStatusCode());
        long responseTime = response.getTime();
        System.out.println("Response Time : "+responseTime);
        System.out.println("Body : "+response.getBody().asString());
        given()
                .spec(request)
                .when().get("/api/users/2")
                .then().log().all()
                .body("data.id", equalTo(2))
                .body("data.email", equalTo("janet.weaver@reqres.in"))
                .body("data.first_name", equalTo("Janet"))
                .body("data.last_name", equalTo("Weaver"))
                .body("data.avatar", equalTo("https://reqres.in/img/faces/2-image.jpg"))
                .body("support.url", equalTo("https://reqres.in/#support-heading"))
                .body("support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"))
                .statusCode(200)
                .time(lessThan(3000l));
                 Assert.assertTrue(responseTime<3000);
    }
    @Test
        //Get Single User with Invalid ID > 10
    public void getsingleuserinvalidId (){
        Response response =   get("https://reqres.in/api/users/100");
        System.out.println("Response : "+response.asString());
        System.out.println("Status Code : "+response.getStatusCode());
        System.out.println("Body : "+response.getBody().asString());}
    @Test
    // Get Single User with Char ID
    public void getsingleuserCharId () {
        Response response =   get("https://reqres.in/api/users/AA");
        System.out.println("Response : "+response.asString());
        System.out.println("Status Code : "+response.getStatusCode());
        System.out.println("Body : "+response.getBody().asString());}
    @Test
    // Get Single User with Special Char
    public void getsingleUserwithSpecChar () {
        Response response =   get("https://reqres.in/api/users/@@");
        System.out.println("Response : "+response.asString());
        System.out.println("Status Code : "+response.getStatusCode());
        System.out.println("Body : "+response.getBody().asString());}



}

