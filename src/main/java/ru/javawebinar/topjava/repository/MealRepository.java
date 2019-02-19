package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface MealRepository {

    Meal save(int userId, Meal meal);

    boolean delete(int userId, int id);

    Meal get(int userId, int id);

    List <Meal> getAll(int userId);
}
