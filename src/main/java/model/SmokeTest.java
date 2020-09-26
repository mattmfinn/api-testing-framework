package model;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import model.json.Endpoints;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SmokeTest implements BaseTest
{
    // Values passed to Gradle
    private String configurationFilePath = System.getProperty("configurationFilePath");
    private int responseMinLength = Integer.decode(System.getProperty("responseMinLength"));
    private String headerName = System.getProperty("headerName");
    private String headerValue = System.getProperty("headerValue");

    // Values set during runtime
    protected ValidatableResponse response;
    protected String uniqueIdentifier;
    protected RequestSpecification createRequest;
    protected RequestSpecification updateRequest;
    protected RequestSpecification deleteRequest;

    /**
     * This TestNG DataProvider reads from a JSON config file, and feeds an endpoint to each test referencing it.
     * We can later create types of tests where these JSON arrays would contain more than just the endpoint,
     * such as a JSON body or flag
     * @return An array for each endpoint under test. The 2D array deserialized from JSON should follow the format
     * @see Endpoints
     *
     */
    @Override
    @DataProvider
    public Object[][] defaultDataProvider()
    {
        Endpoints endpoints = null;
        try
        {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(configurationFilePath));
            endpoints = gson.fromJson(reader, Endpoints.class);
            reader.close();
        }
        catch (IOException e)
        {
            Reporter.log(e.getMessage());
        }

        // Set some variables from data set in the JSON file, as well as RequestSpecifications w/ CLI values
        RestAssured.baseURI = endpoints.getBaseURI();
        uniqueIdentifier = endpoints.getUniqueIdentifier();
        createRequest = RestAssured.given().header(headerName, headerValue).body(endpoints.getCreateRequestBody());
        updateRequest = RestAssured.given().header(headerName, headerValue).body(endpoints.getUpdateRequestBody());
        deleteRequest = RestAssured.given().header(headerName, headerValue);

        return endpoints.getEndpoints();
    }

    /**
     * Record the response body in the test report
     */
    @Override
    public void recordResponseBody()
    {
        Reporter.log(response.extract().response().body().prettyPrint());
    }

    protected boolean isBodyGreaterEqualToMinimumLength()
    {
        return response.extract().response().body().toString().length() >= responseMinLength;
    }
}
