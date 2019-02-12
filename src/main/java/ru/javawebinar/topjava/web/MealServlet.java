package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

public class MealServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        List<MealTo> mealTos = MealsUtil.getFilteredWithExcessInOnePass(MealsUtil.meals, LocalTime.of(7, 0), LocalTime.of(15, 0), 2000);
        request.setAttribute("mealsTo", mealTos);
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }

}
