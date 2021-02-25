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

import java.io.IOException;

public class Login {

    @FXML
    private TextField password;

    @FXML
    private Button login;

    Parent root;

    @FXML
    void loginclick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/sample/fxml/Emballages.fxml"));

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.getScene().setRoot(root);
        window.show();

    }

}