package org.adidas.common.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.util.Objects.isNull;

public class DateUtil {

    public static String getCurrentDate() {
        LocalDate localDate = LocalDate.now();
        return localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    private static String getCurrentDateTime = null;

    public static String getCurrentDateTime() {
        if (isNull(getCurrentDateTime)) {
            LocalDateTime localDateTime = LocalDateTime.now();
            getCurrentDateTime = localDateTime.format(DateTimeFormatter.ofPattern("yyyy_MM_dd_h_m_s"));
        }
        return getCurrentDateTime;
    }

}
