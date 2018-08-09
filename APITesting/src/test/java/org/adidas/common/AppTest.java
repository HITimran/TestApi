package org.adidas.common;

import org.adidas.dataProvider.CityName;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.IOException;

public class AppTest extends commonSteps implements CommonSetup {

    @Test(dataProvider = "getCityName", dataProviderClass = CityName.class)
    public void getWeatherDetails(String cityName) throws IOException, ParseException {
        getResponse(cityName);
        getWeatherList();
        markStatus();
        getWeather();
    }
}
