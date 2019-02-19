package ru.javawebinar.topjava.util;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static boolean isBetween(Temporal td, Temporal start, Temporal end) {


        try {
            LocalTime localTimeTemp = LocalTime.from(td);
            LocalTime starTimeTemp = LocalTime.from(start);
            LocalTime endTimeTemp = LocalTime.from(end);

            return localTimeTemp.compareTo(starTimeTemp) >= 0 && localTimeTemp.compareTo(endTimeTemp) <= 0;
        } catch (DateTimeException e) {
            LocalDate localDateTemp = LocalDate.from(td);
            LocalDate starDateTemp = LocalDate.from(start);
            LocalDate endDateTemp = LocalDate.from(end);

            return localDateTemp.compareTo(starDateTemp) >= 0 && localDateTemp.compareTo(endDateTemp) <= 0;

        }
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }

}
