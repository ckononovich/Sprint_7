import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

public class TestListOfOrders {

    Steps steps = new Steps();

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

    @Test

    public void getAllOrders(){

        Response response = steps.allOrders();
        steps.checkResponse(response,200);
        steps.printResponseBodyToConsole(response);
    }

}
