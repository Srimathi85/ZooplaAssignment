package com.zoopla.TestDataProvider;

import org.testng.annotations.DataProvider;

public class Dataprovider {
    @DataProvider(name = "location_data")
    public Object[][] location_data() {

        return new String[][]{{"London"}};
    }
}

