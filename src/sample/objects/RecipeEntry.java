package sample.objects;

public class RecipeEntry {
    public String ingName;
    public int purcentage;
    public int gram;
    public int ml;

    public RecipeEntry() {
    }

    public String getIngName() {
        return ingName;
    }

    public void setIngName(String ingName) {
        this.ingName = ingName;
    }

    public int getPurcentage() {
        return purcentage;
    }

    public void setPurcentage(int purcentage) {
        this.purcentage = purcentage;
    }

    public int getGram() {
        return gram;
    }

    public void setGram(int gram) {
        this.gram = gram;
    }

    public int getMl() {
        return ml;
    }

    public void setMl(int ml) {
        this.ml = ml;
    }
}