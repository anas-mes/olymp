package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;


import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.objects.Package;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EntryDialog implements Initializable{



    @FXML
    private ChoiceBox<String> choice = new ChoiceBox<>();
    @FXML
    private static Label description ;

    @FXML
    private Spinner<Integer> quantity;

    @FXML
    private Button add;

    ObservableList<Package> packages = FXCollections.observableArrayList();





    @FXML
    void addPackage(ActionEvent event) {
       Stage stage = (Stage)((Node)( event).getSource()).getScene().getWindow();
   stage.close();}



    public static void displayDialog(ObservableList<Package> packages) throws IOException {
        Stage window = new Stage();
        Parent root = FXMLLoader.load(EntryDialog.class.getResource("/sample/fxml/EntryDialog.fxml"));
        window.setScene(new Scene(root));
        window.setTitle("add");
        window.showAndWait();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}









