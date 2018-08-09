package org.adidas.common;

import io.restassured.RestAssured;
import org.adidas.common.globalLibrary.Ex;
import org.adidas.common.utils.jsonUtils.CityList;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import static org.adidas.common.utils.Files.TMP_DIRECTORY;
import static org.adidas.common.utils.Files.cleanDirectory;

public interface CommonSetup {
    Logger log = Logger.getLogger(CommonSetup.class.getName());

    CityList cityList=new CityList();
    String EXCEL_RESULT_PATH = TMP_DIRECTORY + "\\resultFile.xlsx";

    @BeforeTest(alwaysRun = true)
    default void beforeTest() {
        log.info("Before Test method");
        cleanDirectory(TMP_DIRECTORY);
        RestAssured.baseURI = "https://api.openweathermap.org/data/2.5";

    }

    @AfterTest
    default void afterTest() {
        log.info("After Test method");
    }

    @AfterSuite(alwaysRun = true)
    default void AfterSuite() {
        log.info("After Suite method");
        Ex.getTestData().excelWriteOut(EXCEL_RESULT_PATH);
    }
}
