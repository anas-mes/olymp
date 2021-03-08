package sample.objects;

import java.util.HashMap;
import java.util.Map;

public class Recette {
    Map<Ingredient, Integer> ingredients = new HashMap<>();
    Map<Ingredient, Integer> calculating = new HashMap<>();
    private String name ;
    private String recette_id ;

    public Map<Ingredient, Integer> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Map<Ingredient, Integer> ingredients) {
        this.ingredients = ingredients;
    }

    public Map<Ingredient, Integer> getCalculating() {
        return calculating;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRecette_id() {
        return recette_id;
    }

    public void setRecette_id(String recette_id) {
        this.recette_id = recette_id;
    }

    public Recette(){

    }
    public Recette(String name, String recette_id) {
        this.name = name;
        this.recette_id = recette_id;
    }

    public Map<Ingredient,Integer> resizeBatch(int quantity){
        int value;
        for ( Ingredient key : ingredients.keySet() ) {
            value =( ingredients.get(key)*quantity)/100;
        calculating.putIfAbsent(key,value);
        }
        return calculating;

    }

    public void addIngredient(Ingredient ing, int purcetage){
        ingredients.put(ing,purcetage);
    }


}
