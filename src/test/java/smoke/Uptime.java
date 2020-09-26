package smoke;

import model.SmokeTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

/**
 * This test is designed to determine if an endpoint is currently 'up'.
 * We will test the following: get (all), post (create), get (single result), put (update), delete.
 * These tests are sequential so that we can use the same 'uniqueIdentifier' value for the tests
 * that will perform an HTTP request (on the same entry). After each request is a test to confirm that the
 * response body is greater than the minimum expected size, or 'responseMinLength', set when running the Gradle task.
 * Testing 'responseMinLength' must also be dependent on two conditions and should not run otherwise:
 * 1. The HTTP request was made, meaning we should have a response with a body
 * 2. The HTTP request was successful (200)
 * As a result, these tests specify a value for 'dependsOnMethods', which will skip these tests if the
 * conditions above are not met.
 */
public class Uptime extends SmokeTest
{
    // NOTE: Default 'priority' for the @Test annotation in TestNG is 0. Tests without an explicit declaration
    // for this value will run first. It is important we preserve the order of the tests, as they are interdependent.
    @Test(dataProvider = "defaultDataProvider")
    public void Get_GivenEndpointIsUp_WhenRequestAll_ThenStatusCodeIs200(String endpoint)
    {
        response = get(endpoint).then().assertThat().statusCode(200);
    }

    @Test(dependsOnMethods = "Get_GivenEndpointIsUp_WhenRequestAll_ThenStatusCodeIs200")
    public void Get_GivenHasResponse_WhenRequestedAll_ThenBodyIsGreaterEqualToMinimumLength()
    {
        Assert.assertTrue(isBodyGreaterEqualToMinimumLength());
    }

    @Test(dataProvider = "defaultDataProvider")
    public void Post_GivenEndpointIsUp_WhenPostNewEntry_ThenStatusCodeIs200(String endpoint)
    {
        response = createRequest.post(endpoint).then().assertThat().statusCode(200);
    }

    @Test(dependsOnMethods = "Post_GivenEndpointIsUp_WhenPostNewEntry_ThenStatusCodeIs200")
    public void Post_GivenHasResponse_WhenPostedNewEntry_ThenBodyIsGreaterEqualToMinimumLength()
    {
        Assert.assertTrue(isBodyGreaterEqualToMinimumLength());
    }

    @Test(priority = 1, dataProvider = "defaultDataProvider")
    public void Get_GivenEndpointIsUp_WhenRequestSingleEntry_ThenStatusCodeIs200(String endpoint)
    {
        response = get(endpoint + uniqueIdentifier).then().assertThat().statusCode(200);
    }

    @Test(dependsOnMethods = "Get_GivenEndpointIsUp_WhenRequestSingleEntry_ThenStatusCodeIs200")
    public void Get_GivenHasResponse_WhenRequestedSingleEntry_ThenBodyIsGreaterEqualToMinimumLength()
    {
        Assert.assertTrue(isBodyGreaterEqualToMinimumLength());
    }

    @Test(priority = 2, dataProvider = "defaultDataProvider")
    public void Put_GivenEndpointIsUp_WhenUpdateEntry_ThenStatusCodeIs200(String endpoint)
    {
        response = updateRequest.put(endpoint + uniqueIdentifier).then().assertThat().statusCode(200);
    }

    @Test(dependsOnMethods = "Put_GivenEndpointIsUp_WhenUpdateEntry_ThenStatusCodeIs200")
    public void Put_GivenHasResponse_WhenUpdatedEntry_ThenBodyIsGreaterEqualToMinimumLength()
    {
        Assert.assertTrue(isBodyGreaterEqualToMinimumLength());
    }

    @Test(priority = 3, dataProvider = "defaultDataProvider")
    public void Delete_GivenEndpointIsUp_WhenDeleteEntry_ThenStatusCodeIs200(String endpoint)
    {
        response = deleteRequest.delete(endpoint + uniqueIdentifier).then().assertThat().statusCode(200);
    }

    @Test(dependsOnMethods = "Delete_GivenEndpointIsUp_WhenDeleteEntry_ThenStatusCodeIs200")
    public void Delete_GivenHasResponse_WhenDeletedEntry_ThenBodyIsGreaterEqualToMinimumLength()
    {
        Assert.assertTrue(isBodyGreaterEqualToMinimumLength());
    }
}
