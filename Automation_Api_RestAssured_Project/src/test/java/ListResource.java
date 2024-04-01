import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class ListResource {
    RequestSpecification request;
    @BeforeTest
    public void openBrowser(){
        request=given()
                .baseUri("https://reqres.in")
                .log().all();
    }
    @Test
    public void GetListResource(){
        Response response =   get("https://reqres.in/api/unknown");
        System.out.println("Response : "+response.asString());
        System.out.println("Status Code : "+response.getStatusCode());
        long responseTime = response.getTime();
        System.out.println("Response Time : "+responseTime);
        System.out.println("Body : "+response.getBody().asString());
        given()
                .spec(request)
                .when().get("/api/unknown")
                .then().log().all()
                .statusCode(200)
                .time(lessThan(3000l))
                .assertThat().body("page", equalTo(1))
                .assertThat().body("per_page", equalTo(6))
                .body("total", equalTo(12))
                .body("total_pages", equalTo(2))
                .assertThat().body("data.id",hasItems(1,2,3,4,5,6))
                .assertThat().body("data.id",hasSize(6))
                .assertThat().body("data.name",contains("cerulean","fuchsia rose","true red","aqua sky","tigerlily","blue turquoise"))
                .assertThat().body("data.year",hasItems(2000,2001,2002,2003,2004,2005))
                .assertThat().body("data.color",hasItems("#98B2D1","#C74375","#BF1932","#7BC4C4","#E2583E","#53B0AE"),
                "data.pantone_value",containsInAnyOrder("15-5217","17-1456","14-4811","19-1664","17-2031","15-4020"))
                .body("support.url", equalTo("https://reqres.in/#support-heading"))
                .body("support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));
                Assert.assertTrue(responseTime<3000);



    }
}
