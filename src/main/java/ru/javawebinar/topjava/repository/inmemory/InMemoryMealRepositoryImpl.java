package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {

    private static Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();

    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(meal -> this.save(1, meal));
    }

    @Override
    public Meal save(int userId, Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            if (repository.get(userId) != null) {
                repository.get(userId).put(meal.getId(), meal);
            } else {
                repository.put(userId, new ConcurrentHashMap<>());
                repository.get(userId).put(meal.getId(), meal);
            }
            return meal;
        }
        return repository.get(userId).computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int userId, int id) {
        if (repository.get(userId) != null) {
            return repository.get(userId).remove(id) != null;
        }
        return false;
    }

    @Override
    public Meal get(int userId, int id) {
        if (repository.get(userId) != null) {
            return repository.get(userId).get(id);
        }
        return null;
    }

    @Override
    public List<Meal> getAll(int userId) {

        if (repository.get(userId) != null) {
            return repository.get(userId).values().stream().sorted((s1, s2) -> s2.getDateTime().compareTo(s1.getDateTime())).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }


}

