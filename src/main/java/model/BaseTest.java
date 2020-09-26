package model;

import io.restassured.response.ValidatableResponse;

public interface BaseTest
{
    Object[][] defaultDataProvider();

    void recordResponseBody();
}
