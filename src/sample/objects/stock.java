package sample.objects;

public class stock {
    private String product_id;
    private String description;
    private String categorie ;
    private int stock;

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public stock(String product_id, String description, String categorie, int stock) {
        this.product_id = product_id;
        this.description = description;
        this.categorie = categorie;
        this.stock = stock;
    }
}
