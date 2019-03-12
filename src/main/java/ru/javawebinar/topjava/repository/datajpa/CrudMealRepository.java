package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true, propagation= Propagation.SUPPORTS)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @Override
    @Transactional
    Meal save (Meal meal);

    @Override
    Optional<Meal>findById (Integer i);

    List<Meal>findByUser_IdOrderByDateTimeDesc(Integer integer);

    List<Meal>findByUser_IdAndDateTimeBetweenOrderByDateTimeDesc(Integer integer, LocalDateTime startDate, LocalDateTime endDate);

    @Transactional
    int deleteByIdAndUser_Id(Integer mealId, Integer userId);

    @Query("SELECT p FROM Meal p JOIN FETCH p.user WHERE p.id=:id")
    Optional<Meal> findByIdAndFetchUserEagerly(@Param("id") int id);
}
