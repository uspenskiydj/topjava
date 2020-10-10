package ru.javawebinar.topjava.repository;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import java.util.List;
import static org.slf4j.LoggerFactory.getLogger;

public class MealRepositoryImpl implements MealRepository {
    private static final Logger log = getLogger(MealRepositoryImpl.class);

    @Override
    public void save(Meal meal) {
        MealsContainer.meals.add(meal);
    }

    @Override
    public void deleteById(int mealId) {
        MealsContainer.meals.removeIf(m -> m.getId() == mealId);
    }

    @Override
    public Meal getById(int mealId) {
        return MealsContainer.meals.stream()
                .filter(m -> m.getId() == mealId)
                .findAny()
                .orElse(null);
    }

    @Override
    public List<MealTo> getAllMeals() {
        return MealsUtil.filteredByStreams(MealsContainer.meals);
    }

    @Override
    public void update(Meal newMeal) {
        Meal oldMeal = getById(newMeal.getId());
        oldMeal.setCalories(newMeal.getCalories());
        oldMeal.setDateTime(newMeal.getDateTime());
        oldMeal.setDescription(newMeal.getDescription());
    }
}
