package me.shulinina.web36.service;
import me.shulinina.web36.model.Ingredient;
import java.util.Collection;
public interface IngredientService {
    long addIngredient(Ingredient ingredient);
    Ingredient getIngredient(long id);
    Collection<Ingredient> getAll();
    Ingredient editIngredient(long id, Ingredient ingredient);
    boolean deleteIngredient(long id);
    void deleteAllIngredient();
}