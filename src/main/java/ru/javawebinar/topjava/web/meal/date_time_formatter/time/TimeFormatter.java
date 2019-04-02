package ru.javawebinar.topjava.web.meal.date_time_formatter.time;


import org.springframework.expression.ParseException;
import org.springframework.format.Formatter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

public class TimeFormatter implements Formatter<LocalTime> {

    @Override
    public String print(LocalTime object, Locale locale) {
        return "";
    }

    @Override
    public LocalTime parse(String formatted, Locale locale) throws ParseException {
        return parseLocalTime(formatted);
    }


}