public class ResponseMessage {
    private static final Boolean TRUECREATIONOFTHECOURIER = true;
    private static final String COURIERWITHTHESAMELOGIN = "Этот логин уже используется. Попробуйте другой.";
    private static final String NOTENOUGHDATA = "Недостаточно данных для создания учетной записи";
    private static final String INCORRECTDATAFORLOGIN = "Учетная запись не найдена";
    private static final String NOTENOUGHDATAFORLOGIN = "Недостаточно данных для входа";

    public Boolean getTrueCreationOfTheCourier(){
        return TRUECREATIONOFTHECOURIER;
    }
    public String getCourierWithTheSameLogin(){
        return COURIERWITHTHESAMELOGIN;
    }
    public String getNotEnoughData(){
        return NOTENOUGHDATA;
    }
    public String getIncorrectDataFortLogin(){
        return INCORRECTDATAFORLOGIN;
    }
    public String getNotEnoughDataForLogin(){
        return NOTENOUGHDATAFORLOGIN;
    }
}