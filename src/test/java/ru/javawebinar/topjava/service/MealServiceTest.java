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
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration(value = "classpath:spring/spring-db.xml")
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
        Meal meal = service.get(MEAL_ONE_ID, SecurityUtil.authUserId());

        assertMatch(meal, MEAL_ONE);
    }

    @Test(expected = NotFoundException.class)
    public void getByWrongUser() throws Exception {
        service.get(MEAL_ONE_ID, SecurityUtil.authUserId() + 1);
    }

    @Test(expected = NotFoundException.class)
    public void getByWrongId() throws Exception {
        service.get(25, SecurityUtil.authUserId());
    }


    @Test
    public void delete() throws Exception {
        service.delete(MEAL_ONE_ID, SecurityUtil.authUserId());
        assertMatch(service.getAll(SecurityUtil.authUserId()), MEAL_TWO);
    }

    @Test(expected = NotFoundException.class)
    public void deletedByWrongUser() throws Exception {
        service.delete(MEAL_ONE_ID, SecurityUtil.authUserId() + 1);
    }

    @Test(expected = NotFoundException.class)
    public void deletedByWrongId() throws Exception {
        service.delete(25, SecurityUtil.authUserId());
    }

    @Test
    public void getBetweenDateTimes() throws Exception {
        List<Meal> meals = service.getBetweenDateTimes(LocalDateTime.of(2019, Month.FEBRUARY, 23, 8, 0),
                LocalDateTime.of(2019, Month.FEBRUARY, 24, 23, 0),
                SecurityUtil.authUserId());
        assertMatch(meals, MEAL_ONE);
    }

    @Test
    public void getAll() throws Exception {
        List<Meal> meals = service.getAll(SecurityUtil.authUserId());
        assertMatch(meals, MEAL_TWO, MEAL_ONE);

    }

    @Test
    public void update() throws Exception {
        Meal meal = new Meal(MEAL_ONE);
        meal.setCalories(700);
        meal.setDescription("Полдник");
        service.update(meal, SecurityUtil.authUserId());
        assertMatch(service.get(MEAL_ONE_ID, SecurityUtil.authUserId()), meal);
    }

    @Test(expected = NotFoundException.class)
    public void updateByWrongUser() throws Exception {

        Meal newMeal = new Meal(MEAL_ONE);
        newMeal.setId(44);
        service.update(newMeal, SecurityUtil.authUserId());
    }

    @Test(expected = NotFoundException.class)
    public void updateByWrongId() throws Exception {
        service.update(MEAL_ONE, SecurityUtil.authUserId() + 1);
    }

    @Test
    public void create() throws Exception {
        Meal newMeal = new Meal(null, LocalDateTime.of(2019, Month.FEBRUARY, 26, 13, 0), "Обед", 500);
        Meal created = service.create(newMeal, SecurityUtil.authUserId());
        newMeal.setId(created.getId());
        assertMatch(service.getAll(SecurityUtil.authUserId()), newMeal, MEAL_TWO, MEAL_ONE);
    }

}