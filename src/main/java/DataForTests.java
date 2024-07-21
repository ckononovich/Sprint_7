public class DataForTests {
    LoginCourier loginWithEmptyData = new LoginCourier("", "");
    Order order = new Order();
    private final String login = "User6";
    private final String wrongLogin = "User77";
    private final String password = "5555";
    LoginCourier loginCourier = new LoginCourier(login, password);
    LoginCourier loginWithWrongLogin = new LoginCourier(wrongLogin, password);
    private final String wrongPassword = "1111";
    LoginCourier loginWithWrongPassword = new LoginCourier(login, wrongPassword);
    private final String firstName = "New";
    CourierData courierData = new CourierData(login, password, firstName);
    CourierData courierDataNew = new CourierData("", "", firstName);
}