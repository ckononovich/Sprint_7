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

public class TestCreateCourier {

    private String login = "User2";
    private String password = "5555";
    private String firstName = "New";
    CourierData courierData = new CourierData(login, password, firstName);

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }


    @Test

    @DisplayName("Create a new courier")
    @Description("Checking creation of a new courier is important")

    public void createNewCourier(){

        Response response = createCourier();
        checkResponse(response, 201,true);
        printResponseBodyToConsole(response);
    }

    @Test
    @DisplayName("Create the courier with the same login")
    @Description("Test negative experience with the same login")

    public void createCourierWithTheSameLogin(){

        Response createCourier = createCourier();
        Response createTheSameCourier = createCourier();
        compareResponseFromTheServer(createTheSameCourier, 409,"Этот логин уже используется. Попробуйте другой.");
        printResponseBodyToConsole(createTheSameCourier);
    }

    @Step ("Send post request in order to create a new courier")

    public Response createCourier(){
        Response response = given().header("Content-type", "application/json").and().body(courierData).when().post("/api/v1/courier");
        return response;
    }

    @Step ("Compare response from the server")

    public void compareResponseFromTheServer(Response response, int code,  String message){
        response.then().statusCode(code).and().assertThat().body("message", equalTo(message));
    }

    @Step ("Check the response from the server")
    public void checkResponse(Response response,int code ,Boolean message){
        response.then().statusCode(code).and().assertThat().body("ok",equalTo(message));
    }

    @Step ("Print response body to console")

    public void printResponseBodyToConsole(Response response){
        System.out.println(response.body().asString());
    }

    @Test

    @DisplayName("Create a new courier")
    @Description("Checking creation of a new courier is important")

    public void checkAllRequiredFields(){
        Response response = sendPostRequestWithoutSomeFields();
        checkResponseFromServer(response, 400,"Недостаточно данных для создания учетной записи");
        printResponseBodyToConsole(response);
    }

    @Step ("Send post request with some empty values")

    public Response sendPostRequestWithoutSomeFields(){
        CourierData courierDataNew = new CourierData("","",firstName);
        Response response = given().header("Content-type", "application/json").and().body(courierDataNew).when().post("/api/v1/courier");
        return response;
    }

    @Step ("Check the response from the server")
    public void checkResponseFromServer(Response response,int code ,String message){
        response.then().statusCode(code).and().assertThat().body("message",equalTo(message));
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
