package crud;

import models.BaseTestImplementation;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class GetEndPoint extends BaseTestImplementation
{
    public GetEndPoint(String baseUrl, String resource, boolean supportsDelete)
    {
        super(baseUrl, resource, supportsDelete);
    }

    @Test
    public void checkResponseCode_isOK()
    {

    }
}
