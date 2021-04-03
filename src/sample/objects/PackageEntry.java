package sample.objects;

public class PackageEntry {
    //java.util.Date dt = new java.util.Date();
    //java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
    String currentTime ;
            //= sdf.format(dt);
    String registerer ;
    String action ;
    String articles;

    public PackageEntry(String currentTime, String registerer, String action, String articles) {
        this.currentTime = currentTime;
        this.registerer = registerer;
        this.action = action;
        this.articles = articles;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getRegisterer() {
        return registerer;
    }

    public void setRegisterer(String registerer) {
        this.registerer = registerer;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getArticles() {
        return articles;
    }

    public void setArticles(String articles) {
        this.articles = articles;
    }
}
