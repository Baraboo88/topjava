package ru.javawebinar.topjava.web.meal.date_time_formatter.date;


import org.springframework.expression.ParseException;
import org.springframework.format.Formatter;

import java.time.LocalDate;
import java.util.Locale;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;

public class DateFormatter implements Formatter<LocalDate> {

    @Override
    public String print(LocalDate object, Locale locale) {
        return "";
    }

    @Override
    public LocalDate parse(String formatted, Locale locale) throws ParseException {
        return parseLocalDate(formatted);
    }


}