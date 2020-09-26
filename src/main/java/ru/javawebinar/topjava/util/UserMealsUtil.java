package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);

        List<UserMealWithExcess> mealsTo2 = filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo2.forEach(System.out::println);
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMeal> mealsFilteredByTime = new ArrayList<>();
        Map<LocalDate, Integer> dateCalories = new HashMap<>();
        for (UserMeal meal : meals) {
            LocalDateTime mealDateTime = meal.getDateTime();
            dateCalories.merge(mealDateTime.toLocalDate(), meal.getCalories(), Integer::sum);
            if (TimeUtil.isBetweenHalfOpen(mealDateTime.toLocalTime(), startTime, endTime)) {
                mealsFilteredByTime.add(meal);
            }
        }
        List<UserMealWithExcess> mealsWithExcess = new ArrayList<>();
        for (UserMeal meal : mealsFilteredByTime) {
            LocalDateTime mealDateTime = meal.getDateTime();
            mealsWithExcess.add(new UserMealWithExcess(mealDateTime, meal.getDescription(), meal.getCalories(),
                    dateCalories.get(mealDateTime.toLocalDate()) > caloriesPerDay));
        }
        return mealsWithExcess;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> dateCalories = meals.stream()
                .collect(Collectors.toMap((k) -> k.getDateTime().toLocalDate(), UserMeal::getCalories, Integer::sum));
        List<UserMealWithExcess> mealsWithExcess = meals.stream()
                .filter((k) ->  TimeUtil.isBetweenHalfOpen(k.getDateTime().toLocalTime(), startTime, endTime))
                .map((m) -> (new UserMealWithExcess(m.getDateTime(), m.getDescription(), m.getCalories(),
                        dateCalories.get(m.getDateTime().toLocalDate()) > caloriesPerDay)))
                .collect(Collectors.toList());
        return mealsWithExcess;
    }
}
