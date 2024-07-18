import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestLoginCourier {

    Steps steps = new Steps();

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

    @Test
    @DisplayName("Login to the system")
    @Description("Smoke test")
    public void loginToTheSystem(){
        Response createUser = steps.createCourier();
        Response login = steps.loginToTheSystemCorrectData();
        int id = login.getBody().path("id");
        steps.checkResponseFromServer(login, 200, id);
        steps.printResponseBodyToConsole(login);
    }

    @Test
    @DisplayName("Login is wrong")
    @Description("Testing login with wrong data")

    public void loginWithWrongLogin(){
        Response createUser = steps.createCourier();
        Response login = steps.loginToTheSystemWithWrongLogin();
        steps.compareResponseFromTheServer(login, 404, "Учетная запись не найдена");
        steps.printResponseBodyToConsole(login);
    }

    @Test
    @DisplayName("Password is wrong")
    @Description("Testing login with wrong data")

    public void loginWithWrongPassword(){
        Response createUser = steps.createCourier();
        Response login = steps.loginToTheSystemWithWrongPassword();
        steps.compareResponseFromTheServer(login, 404, "Учетная запись не найдена");
        steps.printResponseBodyToConsole(login);
    }

    @Test
    @DisplayName("Password and login are empty")
    @Description("Testing login with empty data")

    public void loginWithEmptyData(){
        Response createUser = steps.createCourier();
        Response login = steps.loginToTheSystemWithEmptyData();
        steps.compareResponseFromTheServer(login, 400, "Недостаточно данных для входа");
        steps.printResponseBodyToConsole(login);
    }


    @After
    public void deleteCourierData(){
        try {Response response = steps.loginCourier();
            String id = response.getBody().path("id").toString();
            Response deleteCourier = steps.deleteCourier(id);
        } catch (Exception exception){System.out.println("Nothing to delete");}
    }
}
