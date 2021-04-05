package sample.objects;

public class Ingredient {
    private String name;
    private String ingredient_id;
    private int stock;
    private String categorie ;

    public Ingredient(String name, String ingredient_id, int stock, String categorie) {
        this.name = name;
        this.ingredient_id = ingredient_id;
        this.stock = stock;
        this.categorie = categorie;
    }

    public Ingredient() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredient_id() {
        return ingredient_id;
    }

    public void setIngredient_id(String ingredient_id) {
        this.ingredient_id = ingredient_id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
}
