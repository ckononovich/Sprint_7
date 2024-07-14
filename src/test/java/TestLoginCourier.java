import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class TestLoginCourier {
    private String login = "User2";
    private String wrongLogin = "User3";
    private String password = "5555";
    private String wrongPassword = "1111";
    private String firstName = "New";
    CourierData courierData = new CourierData(login, password, firstName);

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

    @Step("Send post request in order to create a new courier")

    public Response createCourier(){
        Response response = given().header("Content-type", "application/json").and().body(courierData).when().post("/api/v1/courier");
        return response;
    }

    @Step ("Login to the system with correct data")

    public Response loginToTheSystemCorrectData(){
        LoginCourier loginCourier = new LoginCourier(login, password);
        Response response = given().header("Content-type", "application/json").and().body(loginCourier).when().post("/api/v1/courier/login");
        return response;
    }

    @Step ("Check the response from the server")
    public void checkResponseFromServer(Response response,int code ,int message){
        response.then().statusCode(code).and().assertThat().body("id",equalTo(message));
    }

    @Step ("Print response body to console")
    public void printResponseBodyToConsole(Response response){
        System.out.println(response.body().asString());
    }

    @Test
    @DisplayName("Login to the system")
    @Description("Smoke test")
    public void loginToTheSystem(){
        Response createUser = createCourier();
        Response login = loginToTheSystemCorrectData();
        int id = login.getBody().path("id");
        checkResponseFromServer(login, 200, id);
        printResponseBodyToConsole(login);
    }
    @Step ("Login to the system with wrong login")
    public Response loginToTheSystemWithWrongLogin(){
        LoginCourier loginCourier = new LoginCourier(wrongLogin, password);
        Response response = given().header("Content-type", "application/json").and().body(loginCourier).when().post("/api/v1/courier/login");
        return response;
    }

    @Step ("Check the response from the server")
    public void responseFromServerWithBadData(Response response,int code ,String message){
        response.then().statusCode(code).and().assertThat().body("message",equalTo(message));
    }

    @Test
    @DisplayName("Login is wrong")
    @Description("Testing login with wrong data")

    public void loginWithWrongLogin(){
        Response createUser = createCourier();
        Response login = loginToTheSystemWithWrongLogin();
        responseFromServerWithBadData(login, 404, "Учетная запись не найдена");
        printResponseBodyToConsole(login);
    }

    @Step ("Login to the system with wrong login")
    public Response loginToTheSystemWithWrongPassword(){
        LoginCourier loginCourier = new LoginCourier(login, wrongPassword);
        Response response = given().header("Content-type", "application/json").and().body(loginCourier).when().post("/api/v1/courier/login");
        return response;
    }

    @Test
    @DisplayName("Password is wrong")
    @Description("Testing login with wrong data")

    public void loginWithWrongPassword(){
        Response createUser = createCourier();
        Response login = loginToTheSystemWithWrongPassword();
        responseFromServerWithBadData(login, 404, "Учетная запись не найдена");
        printResponseBodyToConsole(login);
    }

    @Step ("Login to the system with empty data")
    public Response loginToTheSystemWithEmptyData(){
        LoginCourier loginCourier = new LoginCourier("", "");
        Response response = given().header("Content-type", "application/json").and().body(loginCourier).when().post("/api/v1/courier/login");
        return response;
    }

    @Test
    @DisplayName("Password and login are empty")
    @Description("Testing login with empty data")

    public void loginWithEmptyData(){
        Response createUser = createCourier();
        Response login = loginToTheSystemWithEmptyData();
        responseFromServerWithBadData(login, 400, "Недостаточно данных для входа");
        printResponseBodyToConsole(login);
    }

    @Step ("Login to the system with empty login")
    public Response loginToTheSystemWithEmptyLogin(){
        LoginCourier loginCourier = new LoginCourier("", password);
        Response response = given().header("Content-type", "application/json").and().body(loginCourier).when().post("/api/v1/courier/login");
        return response;
    }

    @Test
    @DisplayName("Login is empty")
    @Description("Testing login with empty data for login")

    public void loginWithEmptyLogin(){
        Response createUser = createCourier();
        Response login = loginToTheSystemWithEmptyLogin();
        responseFromServerWithBadData(login, 400, "Недостаточно данных для входа");
        printResponseBodyToConsole(login);
    }

    @Step ("Login to the system with empty password")
    public Response loginToTheSystemWithEmptyPassword(){
        LoginCourier loginCourier = new LoginCourier(login, "");
        Response response = given().header("Content-type", "application/json").and().body(loginCourier).when().post("/api/v1/courier/login");
        return response;
    }

    @Test
    @DisplayName("Password is empty")
    @Description("Testing login with empty password")

    public void loginWithEmptyPassword(){
        Response createUser = createCourier();
        Response login = loginToTheSystemWithEmptyPassword();
        responseFromServerWithBadData(login, 400, "Недостаточно данных для входа");
        printResponseBodyToConsole(login);
    }


    @After
    public void deleteCourierData(){
        try { LoginCourier loginCourier = new LoginCourier(login, password);
            Response response = given().header("Content-type", "application/json").and().body(loginCourier).when().post("/api/v1/courier/login");
            String id = response.getBody().path("id").toString();
            Response deleteCourier = given().header("Content-type", "application/json").when().delete("/api/v1/courier/"+id);
            } catch (Exception exception){System.out.println("Nothing to delete");}
    }
}
