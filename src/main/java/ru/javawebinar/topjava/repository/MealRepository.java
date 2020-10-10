package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.util.List;

public interface MealRepository {
    void save(Meal meal);
    void deleteById(int id);
    void update(Meal newMeal);
    Meal getById(int id);
    List<MealTo> getAllMeals();

}
