import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class BaseHttpClient {
    public static RequestSpecification baseRequestSpec() {
        return new RequestSpecBuilder().setBaseUri(Url.HOST)
                .addHeader("Content-type", "application/json")
                .setRelaxedHTTPSValidation()
                .build();
    }
}