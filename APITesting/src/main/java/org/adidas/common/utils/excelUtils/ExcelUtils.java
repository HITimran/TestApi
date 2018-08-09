package org.adidas.common.utils.excelUtils;

import org.adidas.common.utils.excelUtils.testLiterals.TestStatus;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

public class ExcelUtils {
    protected Workbook workbook;
    Sheet sheet;

    public Optional<String> getCellValue(String sheetName, String rowValue, String colValue) {
        this.sheet = workbook.getSheet(sheetName);
        return Optional.ofNullable(
                getCellValue(getCell(rowValue, colValue)
                        .orElse(null)));
    }

    public Optional<String> getCellValue(String sheetName, String rowValue, String colValue, TestStatus setTestStatus) {
        this.sheet = workbook.getSheet(sheetName);
        Cell cell = getCell(rowValue, colValue).orElse(null);
        setCellColor(cell, setTestStatus);
        return Optional.ofNullable(
                getCellValue(getCell(rowValue, colValue)
                        .orElse(null)));
    }

    public void setComment(String sheetName, String rowValue, String colValue, String comment) {
        this.sheet = workbook.getSheet(sheetName);
        Cell cell = getCell(rowValue, colValue).orElse(null);
        setComment(cell, Optional.ofNullable(comment));
    }

    public void setComment(String sheetName, String rowValue, String colValue, String comment, TestStatus setTestStatus) {
        this.sheet = workbook.getSheet(sheetName);
        Cell cell = getCell(rowValue, colValue).orElse(null);
        setComment(cell, Optional.ofNullable(comment));
        setCellColor(cell, setTestStatus);
    }

    public Optional<List<String>> getColumnValues(String sheetName, String colValue) {
        int counter = 1;
        List<String> columnValues = new ArrayList<>();
        this.sheet = workbook.getSheet(sheetName);
        Cell col = getCell(colValue).orElse(null);
        String result;
        Boolean run = true;
        if (!isNull(col)) {
            while (run) {
                try {
                    result = getCellValue(getNextRow(counter, col));
                    if (!isNull(result)) {
                        columnValues.add(result);
                    } else {
                        run = false;
                    }
                    counter++;
                } catch (Exception e) {
                    break;
                }
            }
        }
        return Optional.ofNullable(columnValues);
    }

    public void setCellValue(String sheetName, String rowValue, String colValue, String comment) {
        this.sheet = workbook.getSheet(sheetName);
        Cell cell = getCell(rowValue, colValue).orElse(null);
        setDataInCell(cell, Optional.ofNullable(comment));
    }

    public void setCellValue(String sheetName, String rowValue, String colValue, String comment, TestStatus setTestStatus) {
        this.sheet = workbook.getSheet(sheetName);
        Cell cell = getCell(rowValue, colValue).orElse(null);
        setDataInCell(cell, Optional.ofNullable(comment));
        setCellColor(cell, setTestStatus);
    }

    private Cell getNextRow(int counter, Cell col) {
        return sheet.getRow(col.getRowIndex() + counter).getCell(col.getColumnIndex());
    }

    private Cell getNextCol(int counter, Cell row) {
        return sheet.getRow(row.getRowIndex()).getCell(row.getColumnIndex() + counter);
    }

    private Optional<String> getCellValue(Cell row, Cell col) {
        return Optional.ofNullable(getCellValue(sheet.getRow(row.getRowIndex()).getCell(col.getColumnIndex())));
    }

    private Optional<Cell> getCell(String rowValue, String colValue) {
        Cell row, col, desiredCell;

        row = getCell(rowValue).orElse(null);
        col = getCell(colValue).orElse(null);
        if (!(isNull(row) || isNull(col))) {
            desiredCell = getCell(row.getRowIndex(), col.getColumnIndex()).orElse(null);
            return Optional.ofNullable(desiredCell);
        }
        return Optional.empty();
    }

    private Optional<Cell> getCell(String valueToBeSearch) {
        for (Row row : sheet) {
            for (Cell cell : row) {
                if (!isNull(getCellValue(cell)) && getCellValue(cell).equalsIgnoreCase(valueToBeSearch)) {
                    return Optional.ofNullable(cell);
                }
            }
        }
        return Optional.empty();
    }

    private Optional<Cell> getCell(int row, int col) {
        return Optional.ofNullable(sheet.getRow(row).getCell(col));
    }

    private String getCellValue(Cell cell) {
        try {
            CellType cellType = cell.getCellTypeEnum();
            if (cellType == CellType.STRING)
                return cell.getStringCellValue();
            else if (cellType == CellType.NUMERIC)
                return cell.getNumericCellValue() + "".trim();
        } catch (Exception e) {
        }
        return null;
    }

    private void setComment(Cell cell, Optional<String> comment) {
        if (!isNull(cell)) {
            CreationHelper factory = workbook.getCreationHelper();
            Drawing drawing = sheet.createDrawingPatriarch();

            // When the comment box is visible, have it show in a 1x3 space
            ClientAnchor anchor = factory.createClientAnchor();
            anchor.setCol1(cell.getColumnIndex());
            anchor.setCol2(cell.getColumnIndex() + 1);
            anchor.setRow1(cell.getRow().getRowNum());
            anchor.setRow2(cell.getRow().getRowNum() + 3);

            // Create the comment and set the text+author
            Comment comments = drawing.createCellComment(anchor);
            RichTextString str = factory.createRichTextString(comment.orElse(""));
            comments.setString(str);
            comments.setAuthor("Imran");
        }
    }

    private void setCellColor(Cell cell, TestStatus setTestStatus) {
        if (!isNull(cell)) {
            short index = setTestStatus.getColor() == TestStatus.FAIL.getColor() ? IndexedColors.RED.getIndex() :
                    setTestStatus.getColor() == TestStatus.PASS.getColor() ? IndexedColors.GREEN.getIndex() :
                            setTestStatus.getColor() == TestStatus.SKIP.getColor() ? IndexedColors.YELLOW.getIndex() :
                                    IndexedColors.BLUE.getIndex();

            XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
            style.setFillForegroundColor(index);
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell.setCellStyle(style);
        }
    }

    private void setDataInCell(Cell cell, Optional<String> data) {
        cell.setCellValue(data.orElse(" "));
    }
}


