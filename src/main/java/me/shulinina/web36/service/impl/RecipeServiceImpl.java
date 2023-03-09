package me.shulinina.web36.service.impl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.shulinina.web36.model.Recipe;
import me.shulinina.web36.service.RecipeFilesService;
import me.shulinina.web36.service.RecipeService;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import lombok.NonNull;
import javax.annotation.PostConstruct;
@NonNull
@Service
public class RecipeServiceImpl implements RecipeService {
    final private RecipeFilesService recipeFilesService;
    private static Map<Long, Recipe> recipes = new TreeMap<>();
    private static long lastId = 0;
    public RecipeServiceImpl(RecipeFilesService recipeFilesService) {
        this.recipeFilesService = recipeFilesService;
    }
    @PostConstruct
    private void init(){
        readFrommFile();
    }
    @Override
    public long addRecipe(Recipe recipe) {
        recipes.put(lastId, recipe);
        saveToFile();
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
                saveToFile();
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
    //запись в файл
    private void saveToFile(){
        try {
            String json = new ObjectMapper().writeValueAsString(recipes);
            recipeFilesService.saveRecipesToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    //чтение из файла
    private void readFrommFile(){
        try {
            String json = recipeFilesService.readRecipesFromFile();
            recipes = new ObjectMapper().readValue(json, new TypeReference<TreeMap<Long, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}