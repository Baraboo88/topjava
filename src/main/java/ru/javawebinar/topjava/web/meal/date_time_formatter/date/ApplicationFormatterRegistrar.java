package ru.javawebinar.topjava.web.meal.date_time_formatter.date;

import org.springframework.format.FormatterRegistrar;
import org.springframework.format.FormatterRegistry;

public class ApplicationFormatterRegistrar implements FormatterRegistrar {

    @Override
    public void registerFormatters(FormatterRegistry registry) {
        registry.addFormatterForFieldAnnotation(new DateFormatterAnnotationFormatterFactory());
    }
}
