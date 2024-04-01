import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class DelayedResponse {
    RequestSpecification request;
    @BeforeTest
    public void openBrowser(){
        request=given()
                .baseUri("https://reqres.in")
                .log().all();
    }
    @Test
    public void GetDelayedResponse(){
        Response response =   get("https://reqres.in/api/users?delay=3");
        System.out.println("Response : "+response.asString());
        System.out.println("Status Code : "+response.getStatusCode());
        long responseTime = response.getTime();
        System.out.println("Response Time : "+responseTime);
        System.out.println("Body : "+response.getBody().asString());
        given()
                .spec(request)
        .when()
                .get("/api/users?delay=3")
        .then()
                .statusCode(200)
                .time(lessThan(4000l))
                .assertThat().body("page", equalTo(1))
                .assertThat().body("per_page", equalTo(6))
                .assertThat().body("total", equalTo(12))
                .assertThat().body("total_pages", equalTo(2))
                .assertThat().body("data.id", hasItems(1, 2, 3, 4, 5, 6))
                .assertThat().body("data.id", hasSize(6))
                .assertThat().body("data.email", hasItems(
                "george.bluth@reqres.in",
                "janet.weaver@reqres.in",
                "emma.wong@reqres.in",
                "eve.holt@reqres.in",
                "charles.morris@reqres.in",
                "tracey.ramos@reqres.in"))
                .assertThat().body("data.first_name", contains("George", "Janet", "Emma", "Eve", "Charles", "Tracey"))
                .assertThat().body("data.last_name", contains("Bluth", "Weaver", "Wong", "Holt", "Morris", "Ramos"))
                .body("data.avatar", hasItems(
                        "https://reqres.in/img/faces/1-image.jpg",
                        "https://reqres.in/img/faces/2-image.jpg",
                        "https://reqres.in/img/faces/3-image.jpg",
                        "https://reqres.in/img/faces/4-image.jpg",
                        "https://reqres.in/img/faces/5-image.jpg",
                        "https://reqres.in/img/faces/6-image.jpg"))
                .body("support.url", equalTo("https://reqres.in/#support-heading"))
                .body("support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));


    }

}
