package org.adidas.dataProvider;

import org.adidas.common.globalLibrary.Ex;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.adidas.common.utils.excelUtils.testLiterals.TestDataColumn.*;
import static org.adidas.common.utils.excelUtils.testLiterals.TestDataSheetName.*;

public class CityName {

    @DataProvider(name="getCityName")
    public Iterator<Object[]> getCityName() {
     List<String> data1 =  Ex.getTestData().getColumnValues(OpenWeatherApi.name(),Cities.getName()).orElse(null);
        List<Object[]> data = new ArrayList<>();
        for(String d : data1){
            data.add(new String[]{d});
        }
        return data.iterator();
    }
}
