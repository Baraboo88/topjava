package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    
    public static final int MEAL_ONE_ID = START_SEQ+2;
    public static final int MEAL_TWO_ID = START_SEQ+3;

    public static final Meal MEAL_ONE = new Meal(MEAL_ONE_ID, LocalDateTime.of(2019, Month.FEBRUARY, 24, 8, 0), "Завтрак", 500);
    public static final Meal MEAL_TWO = new Meal(MEAL_TWO_ID, LocalDateTime.of(2019, Month.FEBRUARY, 25, 18, 0), "Ужин", 500);

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingDefaultElementComparator().isEqualTo(expected);
    }
}
