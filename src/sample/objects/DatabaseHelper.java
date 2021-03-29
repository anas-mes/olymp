package sample.objects;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseHelper{
    static Connection con=null;
    private static String db = "sql11401476";
    private static String user= "sql11401476";
    private static String pass = "edH9d7bxbn";

    public static Connection getConnection() {
        if (con != null) return con;
        // get db, user, pass from settings file

        return getConnection(db, user, pass);
    }

    private static Connection getConnection(String db_name,String user_name,String password){
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection(
                    "jdbc:mysql://sql11.freemysqlhosting.net:3306/"+user,user,pass);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return con;
    }
}