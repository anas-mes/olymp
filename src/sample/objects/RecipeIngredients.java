package sample.objects;

public class RecipeIngredients {
    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public double getPurcentage() {
        return purcentage;
    }

    public void setPurcentage(double purcentage) {
        this.purcentage = purcentage;
    }

    public String ingredient;
    public double purcentage;

    public RecipeIngredients(String ingredient, double purcentage) {
        this.ingredient = ingredient;
        this.purcentage = purcentage;
    }
}
