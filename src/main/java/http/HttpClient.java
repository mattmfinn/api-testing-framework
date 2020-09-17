package http;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class HttpClient
{
    private OkHttpClient okHttpClient = new OkHttpClient();
    private String baseURL;

    public HttpClient(String baseURL)
    {
        this.baseURL = baseURL;
    }

    public Response post(String json, String uri)
    {
        return new Response.Builder().build();
    }

    public Response get(String uri)
    {
        Request request = new Request.Builder()
                .url(baseURL + uri)
                .build();
        Response response = new Response.Builder().build();

        try
        {
            response = okHttpClient.newCall(request).execute();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        return response;
    }
}
