package model;

/**
 * We will use this class to map our JSON to, through the Gson library
 */
public class Endpoints
{
    private String baseURI;
    private String[][] endpoints;

    public String getBaseURI()
    {
        return baseURI;
    }

    public String[][] getEndpoints()
    {
        return endpoints;
    }
}
