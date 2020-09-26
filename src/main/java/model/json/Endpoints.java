package model.json;

/**
 * We will use this class to map our JSON to, through the Gson library
 */
public class Endpoints
{
    private String baseURI;
    private String[][] endpoints;
    private String uniqueIdentifier;
    private String createRequestBody;
    private String updateRequestBody;

    public String getBaseURI()
    {
        return baseURI;
    }

    public String[][] getEndpoints()
    {
        return endpoints;
    }

    public String getUniqueIdentifier()
    {
        return uniqueIdentifier;
    }

    public String getCreateRequestBody()
    {
        return createRequestBody;
    }

    public String getUpdateRequestBody()
    {
        return updateRequestBody;
    }
}
