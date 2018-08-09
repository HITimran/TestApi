package org.adidas.common.globalLibrary;

import org.adidas.common.utils.excelUtils.ExcelInit;

import static java.util.Objects.isNull;

public class Ex {

    private static final String TEST_DATA_PATH = ".\\src\\test\\resources\\testData.xlsx";
    private static ExcelInit ex = null;

    public static ExcelInit getTestData() {
        if (isNull(ex)) {
            ex = new ExcelInit(TEST_DATA_PATH);
        }
        return ex;
    }
}
