import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static io.restassured.RestAssured.given;

@RunWith(Parameterized.class)
public class TestCreateOrder {

    public String firstName;
    public String lastName;
    public String address;
    public String metroStation;
    public String phone;
    public int rentTime;
    public String deliveryDate;
    public String comment;
    public String[] color;

    public TestCreateOrder(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, String[] color){
        this.firstName=firstName;
        this.lastName=lastName;
        this.address=address;
        this.metroStation=metroStation;
        this.phone=phone;
        this.rentTime=rentTime;
        this.deliveryDate=deliveryDate;
        this.comment=comment;
        this.color=color;
    }

    @Parameterized.Parameters

    public static Object[][] getFieldsForOrder (){
        return new Object[][]{
                {"Мария","Сидорова","Синяя 13","Черкизовская", "79221110512",5,"11.07.2025", "Хочу заказ!",new String[]{"GREY", "BLACK"}},
                {"Евгений","Киров","Улица ИНТЕРЕСНАЯ 12","Черкизовская", "79221110536",5,"26.07.2045", "КАК МОЖНО СКОРЕЕ", new String[]{"GREY"}},
                {"Екатерина","Самцова","Улица Интернациональная 9","Черкизовская", "79221110537",5,"26.07.2025", "Тест", new String[]{"BlACK"}},
                {"Денис","Самураев","Улица Движения 7а","Черкизовская", "79221110538",5,"15.07.2012", "Когда угодно", new String[]{""}},
        };
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

    Order order = new Order(firstName, lastName,address, metroStation, phone, rentTime, deliveryDate,comment,color);

    @Step("Create an order")

    public Response createNewOrder(){
        Response response = given().header("Content-type", "application/json").and().body(order).post("/api/v1/orders");
        return response;
    }
    @Step ("Check response")

    public void checkResponse(Response response, int code){
        response.then().statusCode(code);
    }

    @Step ("Print response body to console")
    public void printResponseBodyToConsole(Response response){
        System.out.println(response.body().asString());
    }

    @Test
    public void createOrder(){
        Response response = createNewOrder();
        checkResponse(response,201);
        printResponseBodyToConsole(response);
    }

}
