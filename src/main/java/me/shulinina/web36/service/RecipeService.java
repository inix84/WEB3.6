package me.shulinina.web36.service;
import me.shulinina.web36.model.Recipe;
import java.util.Collection;
public interface RecipeService {
    long addRecipe(Recipe recipe);
    Recipe getRecipe(long id);
    Recipe editRecipe(long id, Recipe recipe);
    boolean deleteRecipe(long id);
    void deleteAllRecipe();
    Collection<Recipe> getAll();
}