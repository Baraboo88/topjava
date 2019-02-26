package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    
    public static final int USER_ONE_MEAL_ONE_ID = START_SEQ+2;
    public static final int USER_ONE_MEAL_TWO_ID = START_SEQ+3;
    public static final int USER_TWO_MEAL_THREE_ID = START_SEQ+4;
    public static final int USER_TWO_MEAL_FOUR_ID = START_SEQ+5;
    public static final int USER_TWO_MEAL_FIVE_ID = START_SEQ+6;
    public static final int USER_ONE_MEAL_SIX_ID = START_SEQ+7;

    public static final int USER_ONE_ID = 100000;
    public static final int USER_TWO_ID = 100001;

    public static final Meal USER_ONE_MEAL_ONE = new Meal(USER_ONE_MEAL_ONE_ID, LocalDateTime.of(2019, Month.FEBRUARY, 24, 8, 0), "Завтрак", 1010);
    public static final Meal USER_ONE_MEAL_TWO = new Meal(USER_ONE_MEAL_TWO_ID, LocalDateTime.of(2019, Month.FEBRUARY, 24, 13, 0), "Обед", 1000);
    public static final Meal USER_TWO_MEAL_TREE = new Meal(USER_TWO_MEAL_THREE_ID, LocalDateTime.of(2019, Month.FEBRUARY, 24, 20, 0), "Ужин", 500);
    public static final Meal USER_TWO_MEAL_FOUR = new Meal(USER_TWO_MEAL_FOUR_ID, LocalDateTime.of(2019, Month.FEBRUARY, 25, 9, 0), "Завтрак", 1000);
    public static final Meal USER_TWO_MEAL_FIVE = new Meal(USER_TWO_MEAL_FIVE_ID, LocalDateTime.of(2019, Month.FEBRUARY, 25, 14, 0), "Обед", 1000);
    public static final Meal USER_ONE_MEAL_SIX = new Meal(USER_ONE_MEAL_SIX_ID, LocalDateTime.of(2019, Month.FEBRUARY, 25, 21, 0), "Ужин", 500);


    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(expected);
    }
}
