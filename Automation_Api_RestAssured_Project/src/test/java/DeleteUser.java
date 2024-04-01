import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class DeleteUser {
    RequestSpecification request;
    @BeforeTest
    public void OPenBrowser(){
        request=given()
                .baseUri("https://reqres.in")
                .log().all();
    }
    @Test
    public void DeleteUserTest(){
        given()
                .spec(request)
                .log().all()
        .when()
                .delete("/api/users/2")
        .then()
                .assertThat().statusCode(204)
                .time(lessThan(3000l))
                .log().body();

    }

    }

