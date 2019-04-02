package ru.javawebinar.topjava.web.meal.date_time_formatter.time;

import org.springframework.format.FormatterRegistrar;
import org.springframework.format.FormatterRegistry;

public class TimeFormatterRegistrar implements FormatterRegistrar {

    @Override
    public void registerFormatters(FormatterRegistry registry) {
        registry.addFormatterForFieldAnnotation(new TimeFormatterAnnotationFormatterFactory());
    }
}
