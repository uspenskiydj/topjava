package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.MealRepositoryImpl;
import ru.javawebinar.topjava.repository.MealsContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private static String INSERT_OR_UPDATE = "/meal.jsp";
    private static String LIST_MEAL = "/meals.jsp";
    private MealRepository dao;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public MealServlet() {
        super();
        dao = new MealRepositoryImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");
        if (action == null) {
            forward = LIST_MEAL;
            request.setAttribute("meals", dao.getAllMeals());
        } else if (action.equalsIgnoreCase("delete")) {
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            dao.deleteById(mealId);
            request.setAttribute("meals", dao.getAllMeals());
            forward = LIST_MEAL;
        } else if (action.equalsIgnoreCase("update")) {
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            Meal meal = dao.getById(mealId);
            request.setAttribute("meal", meal);
            forward = INSERT_OR_UPDATE;
        } else {
            forward = INSERT_OR_UPDATE;
        }
        request.getRequestDispatcher(forward).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Meal meal = new Meal();
        meal.setDescription(request.getParameter("description"));
        meal.setCalories(Integer.parseInt(request.getParameter("calories")));
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"), formatter);
        meal.setDateTime(dateTime);
        String mealId = request.getParameter("mealId");
        if(mealId == null || mealId.isEmpty()) {
            meal.setId(MealsContainer.counter.incrementAndGet());
            dao.save(meal);
        }
        else {
            meal.setId(Integer.parseInt(mealId));
            dao.update(meal);
        }
        request.setAttribute("meals", dao.getAllMeals());
        request.getRequestDispatcher(LIST_MEAL).forward(request, response);
    }
}
