package org.adidas.common.utils.excelUtils;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class ExcelInit extends ExcelUtils {

    public ExcelInit(String filePath) {
        try {
            FileInputStream excelFile = new FileInputStream(new File(filePath));
            workbook = new XSSFWorkbook(excelFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void excelWriteOut(String outputPath) {
        try {
            FileOutputStream out = new FileOutputStream(outputPath);
            workbook.write(out);
            out.close();
            workbook = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


