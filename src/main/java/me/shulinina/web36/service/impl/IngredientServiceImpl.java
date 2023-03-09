package me.shulinina.web36.service.impl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.shulinina.web36.model.Ingredient;
import me.shulinina.web36.service.IngredientFilesService;
import me.shulinina.web36.service.IngredientService;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
@Service
public class IngredientServiceImpl implements IngredientService {
    final private IngredientFilesService ingredientFilesService;
    private static Map<Long, Ingredient> ingredients = new TreeMap<>();
    private static long lastId = 0;
    public IngredientServiceImpl(IngredientFilesService ingredientFilesService) {
        this.ingredientFilesService = ingredientFilesService;
    }
    @Override
    public long addIngredient(Ingredient ingredient) {
        ingredients.put(lastId, ingredient);
        saveToFile();
        return lastId++;
    }
    @Override
    public Ingredient getIngredient(long id) {
        for (Ingredient n : ingredients.values()) {
            Ingredient ingredient = ingredients.get(id);
            if (ingredient != null) {
                return ingredient;
            }
        }
        return null;
    }
    @Override
    public Collection<Ingredient> getAll() {
        return ingredients.values();
    }
    @Override
    public Ingredient editIngredient(long id, Ingredient ingredient) {
        for (Ingredient n : ingredients.values()) {
            if (ingredients.containsKey(id)) {
                ingredients.put(id, ingredient);
                saveToFile();
                return ingredient;
            }
        }
        return null;
    }
    @Override
    public boolean deleteIngredient(long id) {
        for (Ingredient n : ingredients.values()) {
            if (ingredients.containsKey(id)) {
                ingredients.remove(id);
                return true;
            }
        }
        return false;
    }
    @Override
    public void deleteAllIngredient() {
        ingredients = new TreeMap<>();
    }
    //запись в файл
    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredients);
            ingredientFilesService.saveIngredientsToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    //чтение из файла
    private void readFrommFile() {
        String json = ingredientFilesService.readIngredientsFromFile();
    }
}