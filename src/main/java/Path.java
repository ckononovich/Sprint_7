public class Path {
    private static final String BASEPATHCOURIER = "/api/v1/courier";
    private static final String BASEPATHLOGINCOURIER = "/api/v1/courier/login";
    private static final String BASEPATHORDER = "/api/v1/orders";

    public String getBasePathCourier(){
        return BASEPATHCOURIER;
    }
    public String getBasePathLoginCourier(){
        return BASEPATHLOGINCOURIER;
    }
    public String getBasePathOrder(){
        return BASEPATHORDER;
    }
}