package com.ethpool.monitor.utilities;

import java.time.*;
import java.util.Date;

public class Converters {

    private Converters() {
        throw new IllegalStateException("Utility class");
    }

    public static Date parseLocalDateTimeStringToDate(String time) {

        LocalDateTime dateTime = LocalDateTime.parse(time);

        return java.sql.Date.valueOf(dateTime.toLocalDate());

    }

    public static Date convertsUnixTimestampToDate(Long timestamp) {

        return Date.from(Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime convertsUnixTimestampToLocalDateTime(Long timestamp) {


        return Instant.ofEpochMilli(timestamp*1000).atZone(ZoneId.systemDefault()).toLocalDateTime();

    }

    public static Long convertsLocalDateToLongMilis(LocalDateTime localDateTime) {

        return  localDateTime.toInstant(ZoneOffset.of(ZoneId.systemDefault().getId())).toEpochMilli();
    }
}
