package sample.objects;

public class Fabrication {
    String currenttime;
    String product;
    String estimated;
    String produced;
    String status ;

    public String getCurrenttime() {
        return currenttime;
    }

    public void setCurrenttime(String currenttime) {
        this.currenttime = currenttime;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getEstimated() {
        return estimated;
    }

    public void setEstimated(String estimated) {
        this.estimated = estimated;
    }

    public String getProduced() {
        return produced;
    }

    public void setProduced(String produced) {
        this.produced = produced;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Fabrication(String currenttime, String product, String estimated, String produced, String status) {
        this.currenttime = currenttime;
        this.product = product;
        this.estimated = estimated;
        this.produced = produced;
        this.status = status;
    }
}
