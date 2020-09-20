package models;

import crud.Read;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import java.lang.reflect.Array;

public class BaseTestImplementation implements BaseTestInterface
{
    /**
     * Here we use a DataProvider to handle the logic of reading the JSON file and providing the data
     * to the factory annotation/method
     * @return An array for each endpoint with flags as to which CRUD operations are supported
     */
    @DataProvider
    public Object[][] parseFile()
    {
        // TODO read endpoints from JSON, set boolean values for CRUD, set baseURI
        return new Array[0][0];
    }

    /**
     * Here, in the factory annotation, we instantiate the tests based on what CRUD operation applies
     */
    @Factory(dataProvider = "parseFile")
    public Object[] instantiateTests(String[] endpoint)
    {
        // TODO set boolean values, case/switch for CRUD test types
        return new Object[] {new Read(endpoint[0])};
    }

    @Override
    public void tearDown()
    {
        // TODO Record response body from HTTP request in SL4J, test reports
    }
}
