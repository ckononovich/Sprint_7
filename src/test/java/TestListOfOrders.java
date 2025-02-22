import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

public class TestListOfOrders {

    Steps steps = new Steps();
    ResponseCode code = new ResponseCode();

    @Test
    @DisplayName("Display the list of orders")
    @Description("Testing displaying the whole list of orders")
    public void getAllOrders() {
        Response response = steps.allOrders();
        steps.printResponseBodyToConsole(response);
        steps.checkResponse(response, code.getSuccessfulCode());
    }
}