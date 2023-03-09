package me.shulinina.web36.service;
public interface RecipeFilesService {
    boolean saveRecipesToFile(String json);
    String readRecipesFromFile();
}