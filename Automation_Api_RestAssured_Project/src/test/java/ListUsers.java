import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class ListUsers {
    RequestSpecification request;
    @BeforeTest
    public void OpenBrowser(){
        request=given().baseUri("https://reqres.in")
                .log().all();
    }
    @Test
    public void GetListUsers(){
        Response response =   get("https://reqres.in/api/users?page=2");
        System.out.println("Response : "+response.asString());
        System.out.println("Status Code : "+response.getStatusCode());
        long responseTime = response.getTime();
        System.out.println("Response Time : "+responseTime);
        System.out.println("Body : "+response.getBody().asString());
        given()
                .spec(request)
                .when().get("/api/users?page=2")
                .then().log().all()
                .assertThat().body("page",equalTo(2))
                .assertThat().body("per_page",equalTo(6))
                .assertThat().body("total",equalTo(12))
                .assertThat().body("total_pages",equalTo(2))
                .assertThat().body("data[0].id", equalTo(7))
                .assertThat().body("data.first_name",containsInAnyOrder("Michael","Lindsay","Tobias","Byron","George","Rachel"))
                .assertThat().body("data.last_name",contains("Lawson","Ferguson","Funke","Fields","Edwards","Howell"))
                .assertThat().body("data.id",hasItems(7,8,9,10,11,12))
                .assertThat().body("data.id",hasSize(6))
                .assertThat().body("data[0].email", equalTo("michael.lawson@reqres.in"))
                .assertThat().body("data[5].id", equalTo(12))
                .assertThat().body("data[3].avatar", equalTo("https://reqres.in/img/faces/10-image.jpg"))
                .statusCode(200)
                .time(lessThan(3000L));
                Assert.assertTrue(responseTime<3000);



    }


    }


