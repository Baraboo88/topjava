package ru.javawebinar.topjava.controller;


import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.model.Meal;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MealController extends HttpServlet {


    private static final String INSERT_OR_EDIT = "/meal.jsp";
    private static final String LIST_MEAL = "/listMeal.jsp";
    private MealDao dao;

    public MealController() {
        super();
        dao = new MealDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String forward = "";
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            long mealId = Long.parseLong(request.getParameter("mealId"));
            dao.deleteMeal(mealId);
            forward = LIST_MEAL;
            request.setAttribute("meals", dao.getAllUsers());
        } else if ("edit".equals(action)) {
            forward = INSERT_OR_EDIT;
            long mealId = Long.parseLong(request.getParameter("mealId"));
            request.setAttribute("meal", dao.getMealById(mealId));
        } else if ("listMeal".equals(action)) {
            forward = LIST_MEAL;
            request.setAttribute("meals", dao.getAllUsers());
        } else {
            forward = INSERT_OR_EDIT;
        }

        request.getRequestDispatcher(forward).forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"));

        Meal meal = new Meal(dateTime, request.getParameter("description"), Integer.parseInt(request.getParameter("calories")));

        String mealId = request.getParameter("mealId");

        if(mealId == null || mealId.isEmpty()){
            dao.addMeal(meal);
        }
        else {
            meal.setId(Long.parseLong(mealId));
            dao.updateMeal(meal);
        }


        request.setAttribute("meals", dao.getAllUsers());
        request.getRequestDispatcher(LIST_MEAL).forward(request, response);
    }

}
