package sample.objects;
public class CurrentUser {
    private static final CurrentUser currentuser = new CurrentUser();
    private static int user_id;
    private static String displayName;
    private static String position;

    private CurrentUser(){

    }
    public static  CurrentUser getInstance(){
        return currentuser;
    }

    public static int getUser_id() {
        return user_id;
    }

    public static void setUser_id(int user_id) {
        CurrentUser.user_id = user_id;
    }

    public static String getDisplayName() {
        return displayName;
    }

    public static void setDisplayName(String displayName) {
        CurrentUser.displayName = displayName;
    }

    public static String getPosition() {
        return position;
    }

    public static void setPosition(String position) {
        CurrentUser.position = position;
    }

}
