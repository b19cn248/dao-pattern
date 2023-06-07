package com.ncs.daopattern.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static String getCurrentDateString() {
        return LocalDate.now().toString();
    }
    public static Long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public static String getCurrentDateStringFormat(){
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedDate = currentDate.format(formatter);
        return formattedDate;
    }
}
