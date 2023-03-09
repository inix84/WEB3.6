package me.shulinina.web36.service.impl;
import me.shulinina.web36.service.RecipeFilesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
@Service
public class RecipeFilesServiceImpl implements RecipeFilesService {
    @Value("${path.to.recipes.file}")
    private String recipesFilePath;
    @Value("${name.of.recipes.file}")
    private String recipesFileName;
    //записать файл
    @Override
    public boolean saveRecipesToFile(String json){
        try {
            cleanRecipesFile();
            Files.writeString(Path.of(recipesFilePath, recipesFileName),json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    //прочитать из файла
    @Override
    public String readRecipesFromFile(){
        try {
            return Files.readString(Path.of(recipesFilePath, recipesFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //удалить и очистить файл
    private boolean cleanRecipesFile(){
        try {
            Path path = Path.of(recipesFilePath, recipesFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}