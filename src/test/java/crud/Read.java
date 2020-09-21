package crud;

import model.BaseTestImplementation;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;

public class Read extends BaseTestImplementation
{
    private String endpoint;

    public Read(String endpoint)
    {
        super();
        this.endpoint = endpoint;
    }

    @Test
    public void checkResponseCode_isOK()
    {
        get(endpoint).then().statusCode(404);
    }
}
