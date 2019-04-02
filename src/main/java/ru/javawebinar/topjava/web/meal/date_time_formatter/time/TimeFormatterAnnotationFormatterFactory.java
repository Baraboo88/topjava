package ru.javawebinar.topjava.web.meal.date_time_formatter.time;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;
import ru.javawebinar.topjava.web.meal.date_time_formatter.date.CustomDateFormatter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;

public class TimeFormatterAnnotationFormatterFactory implements AnnotationFormatterFactory<CustomDateFormatter> {

    public Set<Class<?>> getFieldTypes() {
        return new HashSet<Class<?>>(asList(new Class<?>[] {
                LocalTime.class}));
    }

    public Printer<?> getPrinter(CustomDateFormatter annotation, Class<?> fieldType) {
        return new TimeFormatter();
    }

    public Parser<?> getParser(CustomDateFormatter annotation, Class<?> fieldType) {
        return new TimeFormatter();
    }

}
