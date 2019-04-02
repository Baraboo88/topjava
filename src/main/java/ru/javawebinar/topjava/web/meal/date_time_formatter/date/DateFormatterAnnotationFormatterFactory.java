package ru.javawebinar.topjava.web.meal.date_time_formatter.date;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;

public class DateFormatterAnnotationFormatterFactory implements AnnotationFormatterFactory<CustomDateFormatter> {

    public Set<Class<?>> getFieldTypes() {
        return new HashSet<Class<?>>(asList(new Class<?>[]{
                LocalDate.class}));
    }

    public Printer<?> getPrinter(CustomDateFormatter annotation, Class<?> fieldType) {
        return new DateFormatter();
    }

    public Parser<?> getParser(CustomDateFormatter annotation, Class<?> fieldType) {
        return new DateFormatter();
    }

}
