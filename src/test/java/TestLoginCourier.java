import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestLoginCourier {

    Steps steps = new Steps();
    ResponseCode code = new ResponseCode();
    ResponseMessage responseMessage = new ResponseMessage();

    @Before
    public void createACourier() {
        Response createUser = steps.createCourier();
    }

    @Test
    @DisplayName("Login to the system")
    @Description("Testing login with correct data")
    public void loginToTheSystem() {
        Response login = steps.loginToTheSystemCorrectData();
        int id = login.getBody().path("id");
        steps.printResponseBodyToConsole(login);
        steps.checkResponseFromServer(login, code.getSuccessfulCode(), id);
    }

    @Test
    @DisplayName("Login is wrong")
    @Description("Testing login with wrong data for login")
    public void loginWithWrongLogin() {
        Response login = steps.loginToTheSystemWithWrongLogin();
        steps.printResponseBodyToConsole(login);
        steps.compareResponseFromTheServer(login, code.getNotFoundCode(), responseMessage.getIncorrectDataFortLogin());
    }

    @Test
    @DisplayName("Password is wrong")
    @Description("Testing login with wrong data for password")
    public void loginWithWrongPassword() {
        Response login = steps.loginToTheSystemWithWrongPassword();
        steps.printResponseBodyToConsole(login);
        steps.compareResponseFromTheServer(login, code.getNotFoundCode(), responseMessage.getIncorrectDataFortLogin());
    }

    @Test
    @DisplayName("Password and login are empty")
    @Description("Testing login with empty data")
    public void loginWithEmptyData() {
        Response login = steps.loginToTheSystemWithEmptyData();
        steps.printResponseBodyToConsole(login);
        steps.compareResponseFromTheServer(login, code.getBadRequestCode(), responseMessage.getNotEnoughDataForLogin());
    }


    @After
    public void deleteCourierData() {
        try {
            Response response = steps.loginToTheSystemCorrectData();
            String id = response.getBody().path("id").toString();
            Response deleteCourier = steps.deleteCourier(id);
        } catch (Exception exception) {
            System.out.println("Nothing to delete");
        }
    }
}