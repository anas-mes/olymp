package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.objects.CurrentUser;
import sample.objects.DatabaseHelper;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login {

    @FXML
    private TextField password;

    @FXML
    private TextField username;

    @FXML
    private Button login;



    Parent root;
    ResultSet rs;

    Connection con = DatabaseHelper.getConnection() ;
    Statement stmnt ;

    @FXML
    void loginclick(ActionEvent event) throws IOException, SQLException {
        if(check()){
        CurrentUser.setUser_id(rs.getInt(1));
        CurrentUser.setDisplayName("Anas mesbah");
        CurrentUser.setPosition(rs.getString(4));
        root = FXMLLoader.load(getClass().getResource("/sample/fxml/Emballages.fxml"));
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.getScene().setRoot(root);
        window.show();

    }
    }

    public boolean check() throws SQLException {
        String sql = "SELECT * FROM users WHERE user_id='" + username.getText() + "' && password='" + password.getText()+ "'";
        stmnt = con.createStatement();
        rs = stmnt.executeQuery(sql);
      if(rs.next() == false ){
          return false;
      }else{
           return true ;
      }

    }

}