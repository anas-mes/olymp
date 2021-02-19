package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainController {

    @FXML
    private BorderPane borderpane;

    @FXML
    private VBox borderpaneCenter;

    @FXML
    private TableView<?> Packages_stock;

    @FXML
    private TextField identification_text;

    Parent root;


    public void emballage_btn(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/sample/fxml/Emballages.fxml"));
        borderpane.setCenter(root);

    }

    public void matieres_btn(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/sample/fxml/Ingredients.fxml"));
        borderpane.setCenter(root);
    }

    public void recettes_btn(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/sample/fxml/recettes.fxml"));
        borderpane.setCenter(root);
    }

    public void produitsFinaux_btn(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/sample/fxml/Stock.fxml"));
        borderpane.setCenter(root);
    }
}
