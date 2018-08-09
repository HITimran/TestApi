package org.adidas.common.utils.excelUtils.testLiterals;

public enum TestStatus {
    PASS("GREEN"), FAIL("RED"), SKIP("YELLOW"), OTHER("BLUE");
    String getColor;

    TestStatus(String colour) {
        this.getColor = colour;
    }

    public String getColor() {
        return getColor;
    }
}


