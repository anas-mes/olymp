package sample.objects;

public class RecipeEntry {
    public String ingName;
    public double purcentage;
    public double gram;
    public double ml;

    public RecipeEntry(String ingName, double purcentage, double gram, double ml) {
        this.ingName = ingName;
        this.purcentage = purcentage;
        this.gram = gram;
        this.ml = ml;
    }

    public RecipeEntry() {
    }

    public String getIngName() {
        return ingName;
    }

    public void setIngName(String ingName) {
        this.ingName = ingName;
    }

    public double getPurcentage() {
        return purcentage;
    }

    public void setPurcentage(int purcentage) {
        this.purcentage = purcentage;
    }

    public double  getGram() {
        return gram;
    }

    public void setGram(double gram) {
        this.gram = gram;
    }

    public double getMl() {
        return ml;
    }

    @Override
    public String toString() {
        return "RecipeEntry{" +
                "ingName='" + ingName + '\'' +
                ", purcentage=" + purcentage +
                ", gram=" + gram +
                '}';
    }

    public void setMl(double ml) {
        this.ml = ml;
    }

    public double calculateGram(double batch){
        return (this.purcentage*batch)/100;
    }
}