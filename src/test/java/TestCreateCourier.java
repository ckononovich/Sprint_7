import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestCreateCourier {

    Steps steps = new Steps();

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

    @Test

    @DisplayName("Create a new courier")
    @Description("Checking creation of a new courier is important")

    public void createNewCourier(){

        Response response = steps.createCourier();
        steps.checkResponse(response, 201,true);
        steps.printResponseBodyToConsole(response);
    }

    @Test
    @DisplayName("Create the courier with the same login")
    @Description("Test negative experience with the same login")

    public void createCourierWithTheSameLogin(){

        Response createCourier = steps.createCourier();
        Response createTheSameCourier = steps.createCourier();
        steps.compareResponseFromTheServer(createTheSameCourier, 409,"Этот логин уже используется. Попробуйте другой.");
        steps.printResponseBodyToConsole(createTheSameCourier);
    }

    @Test

    @DisplayName("Create a new courier with wrong data")
    @Description("Not all required fields are populated")

    public void checkAllRequiredFields(){
        Response response = steps.sendPostRequestWithoutSomeFields();
        steps.compareResponseFromTheServer(response, 400,"Недостаточно данных для создания учетной записи");
        steps.printResponseBodyToConsole(response);
    }

    @After
    public void deleteCourierData(){
        try {Response response = steps.loginCourier();
        String id = response.getBody().path("id").toString();
        Response deleteCourier = steps.deleteCourier(id);
        } catch (Exception exception){System.out.println("Nothing to delete");}
    }

    }
