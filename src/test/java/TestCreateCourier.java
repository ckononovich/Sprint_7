import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;

public class TestCreateCourier {

    Steps steps = new Steps();
    ResponseCode code = new ResponseCode();
    ResponseMessage responseMessage = new ResponseMessage();

    @Test
    @DisplayName("Create a new courier")
    @Description("Checking creation of a new courier is important")
    public void createNewCourier() {
        Response response = steps.createCourier();
        steps.printResponseBodyToConsole(response);
        steps.checkResponse(response, code.getSuccessfulCodeCreation(), responseMessage.getTrueCreationOfTheCourier());
    }

    @Test
    @DisplayName("Create the courier with the same login")
    @Description("Test negative experience with the same login")
    public void createCourierWithTheSameLogin() {
        Response createCourier = steps.createCourier();
        Response createTheSameCourier = steps.createCourier();
        steps.printResponseBodyToConsole(createTheSameCourier);
        steps.compareResponseFromTheServer(createTheSameCourier, code.getConflictCode(), responseMessage.getCourierWithTheSameLogin());
    }

    @Test
    @DisplayName("Create a new courier with wrong data")
    @Description("Not all required fields are populated")
    public void checkAllRequiredFields() {
        Response response = steps.sendPostRequestWithoutSomeFields();
        steps.printResponseBodyToConsole(response);
        steps.compareResponseFromTheServer(response, code.getBadRequestCode(), responseMessage.getNotEnoughData());
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