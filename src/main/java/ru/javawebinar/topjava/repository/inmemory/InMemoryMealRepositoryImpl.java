package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {

    private static Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();

    private AtomicInteger counter = new AtomicInteger(0);

    private Map<Integer, Meal> getUserMealMap(int userId) {

        return Optional.ofNullable(repository.get(userId)).orElseThrow(() -> new
                NotFoundException("Unknown User ID: " + userId));
    }

    {
        MealsUtil.MEALS.forEach(meal -> this.save(1, meal));
    }

    @Override
    public Meal save(int userId, Meal meal) {

        if (meal.isNew()) {
            repository.computeIfAbsent(userId, key -> new ConcurrentHashMap<>());
            meal.setId(counter.incrementAndGet());
            getUserMealMap(userId).put(meal.getId(), meal);
            return meal;
        }

        return getUserMealMap(userId).computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int userId, int id) {

        return getUserMealMap(userId).remove(id) != null;

    }

    @Override
    public Meal get(int userId, int id) {
        return Optional.ofNullable(getUserMealMap(userId).get(id)).orElseThrow(() -> new
                NotFoundException("Unknown Meal ID " + id + "for user with ID: " + userId));

    }

    @Override
    public List<Meal> getAll(int userId) {

        return getUserMealMap(userId).values().stream()
                .sorted((s1, s2) -> s2.getDateTime().compareTo(s1.getDateTime()))
                .collect(Collectors.toList());

    }

    @Override
    public List<Meal> getAllWithFilter(int userId, LocalTime startTime, LocalTime endTime, LocalDate startDate, LocalDate endDate) {

        return getUserMealMap(userId)
                .values()
                .stream()
                .filter(meal -> DateTimeUtil.isBetween(meal.getTime(), startTime, endTime) && DateTimeUtil.isBetween(meal.getDate(), startDate, endDate))
                .sorted((s1, s2) -> s2.getDateTime().compareTo(s1.getDateTime()))
                .collect(Collectors.toList());
    }


}

