package org.adidas.common.utils.excelUtils.testLiterals;

public enum TestDataColumn {
    Cities("Cities"),
    CurrentTemperature("Current\nTemperature"), TemperatureMinimum("Temperature\nMinimum"), TemperatureMaximum("Temperature\nMaximum"),
    Pressure("Pressure\n(hPa)"), Humidity("Humidity\n(%)"), WindSpeed("Wind\nSpeed (Km/h)"),
    WeatherConditions("Weather\nConditions"), LastRefreshed("Last Refreshed"),Status("Status");

    String getName;

    TestDataColumn(String code) {
        this.getName = code;
    }

    public String getName() {
        return getName;
    }
}