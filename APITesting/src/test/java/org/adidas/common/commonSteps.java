package org.adidas.common;

import io.restassured.http.Method;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import org.adidas.bin.List;
import org.adidas.bin.OpenWeatherApi;
import org.adidas.bin.Weather;
import org.adidas.common.globalLibrary.Ex;
import org.adidas.common.globalLibrary.Get;
import org.adidas.common.utils.excelUtils.testLiterals.TestDataColumn;
import org.adidas.common.utils.excelUtils.testLiterals.TestDataSheetName;
import org.adidas.common.utils.excelUtils.testLiterals.TestStatus;
import org.adidas.common.utils.jsonUtils.CityIdentifier;

import static io.restassured.RestAssured.given;
import static org.adidas.common.globalLibrary.Get.isNotNull;
import static org.adidas.common.utils.StringLiterals.API_STATEMENT;
import static org.adidas.common.utils.excelUtils.testLiterals.TestDataColumn.*;

public class commonSteps implements CommonSetup {
    private java.util.List<List> weatherList;
    Response response;
    String city, cityId;

    protected Response getResponse(String cityName) {
        this.city = cityName;
        this.cityId = cityList.getId(CityIdentifier.name, cityName);
        response = null;
        if (isNotNull(cityId)) {
            response = given()
                    .request(Method.GET, "forecast?id=" + cityId + API_STATEMENT);
        }
        return response;
    }

    protected void markStatus() {
        if (isNotNull(response)) {
            int responseCode = response.getStatusCode();
            Ex.getTestData().setComment(TestDataSheetName.OpenWeatherApi.name(), city, Status.getName(),
                    responseCode + "\t" + Get.getResponse(responseCode).name(),
                    responseCode == 200 ? TestStatus.PASS : TestStatus.FAIL);
        } else {
            Ex.getTestData().setComment(TestDataSheetName.OpenWeatherApi.name(), city, Status.getName(),
                    "Please enter the correct City Name", TestStatus.FAIL);
        }
    }

    protected java.util.List<List> getWeatherList() {
        weatherList = null;
        if (isNotNull(response)) {
            OpenWeatherApi owa = response.as(OpenWeatherApi.class, ObjectMapperType.GSON);
            weatherList = owa.getList();
        }
        return weatherList;
    }

    protected void getWeather() {
        if (isNotNull(weatherList)) {
            for (org.adidas.bin.List individualWeather : weatherList) {
                setDataInExcel(CurrentTemperature, individualWeather.getMain().getTemp().toString());
                setDataInExcel(TemperatureMaximum, individualWeather.getMain().getTempMax().toString());
                setDataInExcel(TemperatureMinimum, individualWeather.getMain().getTempMin().toString());
                setDataInExcel(Pressure, individualWeather.getMain().getPressure().toString());
                setDataInExcel(Humidity, individualWeather.getMain().getHumidity().toString());
                setDataInExcel(WindSpeed, individualWeather.getWind().getSpeed().toString());
                for (Weather weather : individualWeather.getWeather()) {
                    setDataInExcel(WeatherConditions, weather.getDescription());
                    break;
                }
                setDataInExcel(LastRefreshed, individualWeather.getDtTxt());
                break;
            }
        }
    }

    private void setDataInExcel(TestDataColumn column, String value) {
        Ex.getTestData().setCellValue(TestDataSheetName.OpenWeatherApi.name(), city, column.getName(), value);
    }


}
