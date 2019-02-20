package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public class UserServlet extends HttpServlet {

    private MealRestController controller;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        controller = new ClassPathXmlApplicationContext("spring/spring-app.xml").getBean(MealRestController.class);
    }

    private static final Logger log = getLogger(UserServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("forward to users");

        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "userId":
                log.info("userId");

                SecurityUtil.setAuthUserId(Integer.parseInt(request.getParameter("userSelect")));
                log.info("userId {}", SecurityUtil.authUserId());
                request.setAttribute("meals",
                        controller.getMealTo());
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
        }


    }
}
