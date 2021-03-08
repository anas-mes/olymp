package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class EntryDialog {

    private static Stage window = new Stage();
    private static Parent root2 ;
    @FXML
    private ScrollPane sp;

    @FXML
    private Button add_btn;

    @FXML
    private Button confirm_btn;

    @FXML
    private Button cancel_btn;

    @FXML
    private VBox List;



    public EntryDialog() throws IOException {
    }



    public static void displayDialog() throws IOException {
        Parent root = FXMLLoader.load(EntryDialog.class.getResource("/sample/fxml/EntryDialog.fxml"));
        window.initModality(Modality.APPLICATION_MODAL);

        window.setScene(new Scene(root));
        window.showAndWait();


    }

    public void add(ActionEvent actionEvent) throws IOException {
        root2 = FXMLLoader.load(EntryDialog.class.getResource("/sample/fxml/Package_entry.fxml"));
        List.getChildren().add(root2);
    }

    public void confirm(ActionEvent actionEvent) {
    }

    public void cancel(ActionEvent actionEvent) {
        window.close();
    }
}
