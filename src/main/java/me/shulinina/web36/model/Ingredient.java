package me.shulinina.web36.model;
public class Ingredient {
    private final String nameIngredient;
    private final int  amountOfIngredient;     //ингредиенты
    private final String unitOfMeasurement;    //единица измерения
    public Ingredient(String nameIngredient, int amountOfIngredient, String unitOfMeasurement) {
        this.nameIngredient = nameIngredient;
        this.amountOfIngredient = amountOfIngredient;
        this.unitOfMeasurement = unitOfMeasurement;
    }
    @Override
    public String toString() {
        return nameIngredient + amountOfIngredient + unitOfMeasurement;
    }
    public String getNameIngredient() {
        return nameIngredient;
    }
    public int getAmountOfIngredient() {
        return amountOfIngredient;
    }
    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }
}