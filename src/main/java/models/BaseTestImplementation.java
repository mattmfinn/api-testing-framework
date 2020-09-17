package models;

import org.testng.annotations.BeforeClass;

public class BaseTestImplementation implements BaseTestInterface
{
    protected String baseUrl;
    protected String resource;
    protected boolean supportsDelete;

    public BaseTestImplementation(String baseUrl, String resource, boolean supportsDelete)
    {
        this.baseUrl = baseUrl;
        this.resource = resource;
        this.supportsDelete = supportsDelete;
    }

    @Override
    @BeforeClass
    public void setUp()
    {
    }

    @Override
    public void tearDown()
    {
        // Any actions to take at the end of each test class or method
    }
}
