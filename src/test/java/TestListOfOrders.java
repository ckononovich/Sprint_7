import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;

public class TestListOfOrders {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

    @Step("Get all orders")

    public Response allOrders(){
        Response response = given().get("/api/v1/orders");
        return response;
    }

    @Step ("Check status code")
    public void checkResponse(Response response, int code){
        response.then().statusCode(code);
    }

    @Step ("Print response body to console")
    public void printResponseBodyToConsole(Response response){
        System.out.println(response.body().asString());
    }

    @Test

    public void getAllOrders(){

        Response response = allOrders();
        checkResponse(response,200);
        printResponseBodyToConsole(response);
    }

}
