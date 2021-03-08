package sample.objects;

public class Package extends Outils {
    private String name;
    private String package_id;
    private int stock;
    private String categorie ;

    public Package() {

    }

    public Package(String name, String package_id, int stock, String categorie) {
        this.name = name;
        this.package_id = package_id;
        this.stock = stock;
        this.categorie = categorie;
    }

    public String getName() {
        return name;
    }

    public String getPackage_id() {
        return package_id;
    }

    public int getStock() {
        return stock;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPackage_id(String package_id) {
        this.package_id = package_id;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void add(int i){
    stock +=i ;
    }

    public void delete(int i ){
        stock-=i;
    }

    @Override
    public String toString() {
        return "Package{" +
                "name='" + name + '\'' +
                ", package_id='" + package_id + '\'' +
                ", stock=" + stock +
                ", categorie='" + categorie + '\'' +
                '}';
    }

    public void addStock(int i){
        stock=+i;
    }
}
