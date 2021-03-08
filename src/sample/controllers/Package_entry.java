package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Package_entry {
    @FXML
    private Button close;

    @FXML
    void closeEntry(ActionEvent event) {
        Node n = (Node) event.getSource();

        // get node to remove
        //Node p = n.getParent();

        // remove p from parent's child list
        ((VBox) n.getParent()).getChildren().remove(n);

    }

}
