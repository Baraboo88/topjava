package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;


@Controller
@RequestMapping(value = "/meals")
public class JspMealController extends AbstractMealController {

    @GetMapping
    public String getAllMeals(HttpServletRequest request) {
        request.setAttribute("meals", getAll());
        return "meals";
    }

    @GetMapping(value = "delete/{id}")
    public String deleteMeal(@PathVariable("id") String mealId) {
        delete(Integer.parseInt(mealId));
        return "redirect:/meals";
    }

    @GetMapping(value = "create")
    public String createMeal(Model model) {
        final Meal meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000);
        model.addAttribute("meal", meal);
        return "mealForm";
    }

    @GetMapping(value = "update/{id}")
    public String updateMeal(Model model, @PathVariable("id") String mealId) {
        final Meal meal = get(Integer.parseInt(mealId));
        model.addAttribute("meal", meal);
        return "mealForm";
    }

    @PostMapping
    public String addingNewMeal(@RequestParam("dateTime") String dateTime,
                                @RequestParam(value = "description") String description,
                                @RequestParam("id") String id,
                                @RequestParam("calories") String calories
    ) {
        Meal meal = new Meal(
                LocalDateTime.parse(dateTime),
                description,
                Integer.parseInt(calories));
        System.out.println(description);
        if (StringUtils.isEmpty(id)) {
            create(meal);
        } else {
            update(meal, Integer.parseInt(id));
        }
        return "redirect:/meals";
    }

    @PostMapping(value = "filter")
    public String getFiltered(@RequestParam("startDate") String startDate,
                              @RequestParam("endDate") String endDate,
                              @RequestParam("startTime") String startTime,
                              @RequestParam("endTime") String endTime, Model model) {

        LocalDate startLocalDate = parseLocalDate(startDate);
        LocalDate endLocalDate = parseLocalDate(endDate);
        LocalTime startLocalTime = parseLocalTime(startTime);
        LocalTime endLocalTime = parseLocalTime(endTime);
        model.addAttribute("meals", getBetween(startLocalDate, startLocalTime, endLocalDate, endLocalTime));
        return "meals";
    }

}
