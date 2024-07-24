import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

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
    Steps steps = new Steps();
    ResponseCode code = new ResponseCode();

    public TestCreateOrder(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, String[] color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] getFieldsForOrder() {
        return new Object[][]{
                {"Мария", "Сидорова", "Синяя 13", "Черкизовская", "79221110512", 5, "11.07.2025", "Хочу заказ!", new String[]{"GREY", "BLACK"}},
                {"Евгений", "Киров", "Улица ИНТЕРЕСНАЯ 12", "Черкизовская", "79221110536", 5, "26.07.2045", "КАК МОЖНО СКОРЕЕ", new String[]{"GREY"}},
                {"Екатерина", "Самцова", "Улица Интернациональная 9", "Черкизовская", "79221110537", 5, "26.07.2025", "Тест", new String[]{"BlACK"}},
                {"Денис", "Самураев", "Улица Движения 7а", "Черкизовская", "79221110538", 5, "15.07.2012", "Когда угодно", new String[]{""}},
        };
    }

    @Test
    @DisplayName("Create an order")
    @Description("Create an order with different data for the color")
    public void createOrder() {
        Response response = steps.createNewOrder();
        steps.printResponseBodyToConsole(response);
        steps.checkResponse(response, code.getSuccessfulCodeCreation());
    }
}