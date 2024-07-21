public class DataForTests {
    private String login = "User6";
    private String wrongLogin = "User77";
    private String password = "5555";
    private String wrongPassword = "1111";
    private String firstName = "New";

    CourierData courierData = new CourierData(login, password, firstName);
    CourierData courierDataNew = new CourierData("","",firstName);
    LoginCourier loginCourier = new LoginCourier(login, password);
    LoginCourier loginWithWrongLogin = new LoginCourier(wrongLogin,password);
    LoginCourier loginWithWrongPassword = new LoginCourier(login,wrongPassword);
    LoginCourier loginWithEmptyData = new LoginCourier("","");
}
