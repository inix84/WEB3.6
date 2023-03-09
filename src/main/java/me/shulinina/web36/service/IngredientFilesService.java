package me.shulinina.web36.service;
public interface IngredientFilesService {
    boolean saveIngredientsToFile(String json);
    String readIngredientsFromFile();
}