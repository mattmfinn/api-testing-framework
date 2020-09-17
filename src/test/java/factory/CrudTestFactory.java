package factory;

import crud.GetEndPoint;
import org.testng.annotations.Factory;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class CrudTestFactory
{
    @Factory
    public Object[] createCrudTests()
    {
        // First, get the baseUrl for all tests, via gradle ex: '-PbaseUrl=value'
        String baseUrl = System.getProperty("baseUrl");
        // We need to populate two arrays for resources and supportsDelete values
        String[] resourcesArray = new Scanner(System.getProperty("resource")).next().split(",");
        String[] supportsDeleteArray = new Scanner(System.getProperty("supportsDelete")).next().split(",");

        // The array we will store the tests in
        Object[] testsArray = new Object[resourcesArray.length];
        for(int i = 0; i < resourcesArray.length; i++)
        {
            testsArray[i] = new GetEndPoint(baseUrl, resourcesArray[i], Boolean.getBoolean(supportsDeleteArray[i]));
        }
        return testsArray;
    }
}
