import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Steps {
    DataForTests dataForTests =new DataForTests();

    @Step("Send post request in order to create a new courier")
    public Response createCourier(){
        Response response = given().header("Content-type", "application/json").and().body(dataForTests.courierData).when().post("/api/v1/courier");
        return response;
    }

    @Step ("Check the response from the server")
    public void checkResponse(Response response,int code ,Boolean message){
        response.then().statusCode(code).and().assertThat().body("ok",equalTo(message));
    }

    @Step ("Print response body to console")
    public void printResponseBodyToConsole(Response response){
        System.out.println(response.body().asString());
    }

    @Step ("Compare response from the server")
    public void compareResponseFromTheServer(Response response, int code,  String message){
        response.then().statusCode(code).and().assertThat().body("message", equalTo(message));
    }

    @Step ("Send post request with some empty values")
    public Response sendPostRequestWithoutSomeFields(){
        Response response = given().header("Content-type", "application/json").and().body(dataForTests.courierDataNew).when().post("/api/v1/courier");
        return response;
    }

    @Step ("Login to the system with correct data")
    public Response loginToTheSystemCorrectData(){
        Response response = given().header("Content-type", "application/json").and().body(dataForTests.loginCourier).when().post("/api/v1/courier/login");
        return response;
    }

    @Step ("Check the response from the server")
    public void checkResponseFromServer(Response response,int code ,int message){
        response.then().statusCode(code).and().assertThat().body("id",equalTo(message));
    }

    @Step ("Login to the system with wrong login")
    public Response loginToTheSystemWithWrongLogin(){
        Response response = given().header("Content-type", "application/json").and().body(dataForTests.loginWithWrongLogin).when().post("/api/v1/courier/login");
        return response;
    }

    @Step ("Login to the system with wrong login")
    public Response loginToTheSystemWithWrongPassword(){
        Response response = given().header("Content-type", "application/json").and().body(dataForTests.loginWithWrongPassword).when().post("/api/v1/courier/login");
        return response;
    }

    @Step ("Login to the system with empty data")
    public Response loginToTheSystemWithEmptyData(){
        Response response = given().header("Content-type", "application/json").and().body(dataForTests.loginWithEmptyData).when().post("/api/v1/courier/login");
        return response;
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

    @Step("Create an order")

    public Response createNewOrder(){
        Response response = given().header("Content-type", "application/json").and().body(TestCreateOrder.getFieldsForOrder()).post("/api/v1/orders");
        return response;
    }

    public Response loginCourier(){
        Response response = given().header("Content-type", "application/json").and().body(dataForTests.loginCourier).when().post("/api/v1/courier/login");
        return response;
    }

    public Response deleteCourier(String id){
        Response deleteCourier = given().header("Content-type", "application/json").when().delete("/api/v1/courier/"+id);
        return deleteCourier;
    }
}
