package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({"classpath:spring/spring-db.xml", "classpath:spring/spring-app.xml"})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))

public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void get() throws Exception {
        Meal meal = service.get(USER_ONE_MEAL_ONE_ID, USER_ONE_ID);
        assertMatch(meal, USER_ONE_MEAL_ONE);
    }

    @Test(expected = NotFoundException.class)
    public void getByWrongUser() throws Exception {
        service.get(USER_ONE_MEAL_ONE_ID, USER_TWO_ID);
    }


    @Test
    public void delete() throws Exception {
        service.delete(USER_ONE_MEAL_ONE_ID, USER_ONE_ID);
        assertMatch(service.getAll(USER_ONE_ID), USER_ONE_MEAL_SIX, USER_ONE_MEAL_TWO);
    }

    @Test(expected = NotFoundException.class)
    public void deletedByWrongUser() throws Exception {
        service.delete(USER_ONE_MEAL_ONE_ID, USER_TWO_ID);
    }

    @Test
    public void getBetweenDateTimes() throws Exception {
        List<Meal> meals = service.getBetweenDateTimes(LocalDateTime.of(2019, Month.FEBRUARY, 24, 0, 0),
                LocalDateTime.of(2019, Month.FEBRUARY, 24, 23, 0),
                USER_ONE_ID);
        assertMatch(meals, USER_ONE_MEAL_TWO, USER_ONE_MEAL_ONE);
    }

    @Test
    public void getAll() throws Exception {
        List<Meal> meals = service.getAll(USER_ONE_ID);
        assertMatch(meals, USER_ONE_MEAL_SIX, USER_ONE_MEAL_TWO, USER_ONE_MEAL_ONE);

    }

    @Test
    public void update() throws Exception {
        Meal meal = new Meal(USER_ONE_MEAL_ONE);
        meal.setCalories(700);
        meal.setDescription("Полдник");
        service.update(meal, USER_ONE_ID);
        assertMatch(service.get(USER_ONE_MEAL_ONE_ID, USER_ONE_ID), meal);
    }

    @Test(expected = NotFoundException.class)
    public void updateByWrongUser() throws Exception {

        Meal newMeal = new Meal(USER_ONE_MEAL_ONE);
        newMeal.setId(USER_TWO_MEAL_FIVE_ID);
        service.update(newMeal, USER_ONE_ID);
    }


    @Test
    public void create() throws Exception {
        Meal newMeal = new Meal(null, LocalDateTime.of(2019, Month.FEBRUARY, 26, 13, 0), "Обед", 500);
        Meal created = service.create(newMeal, USER_ONE_ID);
        newMeal.setId(created.getId());
        assertMatch(service.getAll(USER_ONE_ID), newMeal,USER_ONE_MEAL_SIX, USER_ONE_MEAL_TWO, USER_ONE_MEAL_ONE);
    }

}