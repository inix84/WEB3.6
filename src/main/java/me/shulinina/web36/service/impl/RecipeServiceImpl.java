package me.shulinina.web36.service.impl;
import me.shulinina.web36.model.Recipe;
import me.shulinina.web36.service.RecipeService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import lombok.NonNull;
@NonNull
@Service
public class RecipeServiceImpl implements RecipeService {
    private static Map<Long, Recipe> recipes = new TreeMap<>();
    private static long lastId = 0;
    @Override
    public long addRecipe(Recipe recipe) {
        recipes.put(lastId, recipe);
        return lastId++;
    }
    @Override
    public Recipe getRecipe(long id) {
        for (Recipe r : recipes.values()) {
            Recipe recipe = recipes.get(id);
            if (recipe != null) {
                return recipe;
            }
        }
        return null;
    }
    @Override
    public Recipe editRecipe(long id, Recipe recipe) {
        for (Recipe r : recipes.values()) {
            if (recipes.containsKey(id)) {
                recipes.put(id, recipe);
                return recipe;
            }
        }
        return null;
    }
    @Override
    public boolean deleteRecipe(long id) {
        for (Recipe r : recipes.values()) {
            if (recipes.containsKey(id)) {
                recipes.remove(id);
                return true;
            }
        }
        return false;
    }
    @Override
    public void deleteAllRecipe() {
        recipes = new TreeMap<>();
    }
    @Override
    public Collection<Recipe> getAll() {
        return recipes.values();
    }
}