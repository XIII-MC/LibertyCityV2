package com.xiii.libertycity.core.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TimeUtil {

    public static String getFullDate() {
        Date date = new Date();
        SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return (dtf.format(date));
    }

    public static String getDate() {
        Date date = new Date();
        SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
        return (dtf.format(date));
    }

    public static String getTime() {
        Date date = new Date();
        SimpleDateFormat dtf = new SimpleDateFormat("HH:mm:ss");
        return (dtf.format(date));
    }

}

