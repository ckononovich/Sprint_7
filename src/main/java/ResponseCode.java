public class ResponseCode {
    private static final int SUCCESSFULCODECREATION = 201;
    private static final int SUCCESSFULCODE = 200;
    private static final int BADREQUESTCODE = 400;
    private static final int NOTFOUNDCODE = 404;
    private static final int CONFLICTCODE = 409;

    public int getSuccessfulCodeCreation (){
        return SUCCESSFULCODECREATION;
    }
    public int getSuccessfulCode (){
        return SUCCESSFULCODE;
    }
    public int getBadRequestCode (){
        return BADREQUESTCODE;
    }
    public int getNotFoundCode (){
        return NOTFOUNDCODE;
    }
    public int getConflictCode (){
        return CONFLICTCODE;
    }
}