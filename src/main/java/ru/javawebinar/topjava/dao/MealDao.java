package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class MealDao {

    private static final ConcurrentHashMap<Long, Meal> meals = new ConcurrentHashMap<>();

    private static AtomicLong idCounter = new AtomicLong();

    public void addMeal(Meal meal) {
        long id = idCounter.getAndIncrement();
        meal.setId(id);
        meals.put(id, meal);
    }

    public void deleteMeal(long id) {
        meals.remove(id);
    }

    public void updateMeal(Meal meal) {
        meals.put(meal.getId(), meal);
    }

    public Map<Long, Meal> getAllUsers() {
        return meals;
    }

    public Meal getMealById(long mealId) {
        return meals.get(mealId);
    }

}
