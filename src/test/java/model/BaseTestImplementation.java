package model;

import com.google.gson.Gson;
import crud.Read;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BaseTestImplementation implements BaseTestInterface
{
    protected String baseURI;

    /**
     * Here we use a DataProvider to handle the logic of reading the JSON file and providing the data
     * to the factory annotation/method
     * @return An array for each endpoint with flags as to which CRUD operations are supported
     */
    @DataProvider
    public Object[][] parseFile()
    {
        Endpoints endpoints = null;
        try
        {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get("/home/matt/IdeaProjects/api-testing-framework/src/test/resources/endpoints.json"));
            endpoints = gson.fromJson(reader, Endpoints.class);
            reader.close();
        }
        catch (IOException e)
        {
            // TODO output error in logs
            System.out.println(e.getMessage());
        }
        baseURI = endpoints.getBaseURI();
        return endpoints.getEndpoints();
    }

    /**
     * Here, in the factory annotation, we instantiate the tests based on what CRUD operation applies
     */
    @Factory(dataProvider = "parseFile")
    public Object[] instantiateTests(String[] endpoint)
    {
        // TODO set boolean values, case/switch for CRUD test types
        System.out.println(endpoint[0]);
        return new Object[] {new Read(endpoint[0])};
    }

    @Override
    @AfterMethod
    public void tearDown()
    {
        // TODO Record response body from HTTP request in SL4J, test reports
    }
}
