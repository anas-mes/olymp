package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import java.net.URL;
import java.util.ResourceBundle;

public class Emballages implements Initializable {


    @FXML
    private TableView<Package> table1;

    @FXML
    private TableColumn<Package, String> package_idColumn;

    @FXML
    private TableColumn<Package, String> descriptionColumn;

    @FXML
    private TableColumn<Package, String> categorieColumn;

    @FXML
    private TableColumn<Package, Integer> stockColumn;

    @FXML
    private TableView<?> table2;

    @FXML
    private TableColumn<?, ?> dateColumn;

    @FXML
    private TableColumn<?, ?> employeeColumn;

    @FXML
    private TableColumn<?, ?> actionColumn;

    @FXML
    private TableColumn<?, ?> articlesColumn;

    @FXML
    private Button newEntry;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        package_idColumn.setCellValueFactory(new PropertyValueFactory<>("package_id"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        categorieColumn.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        table1.getItems().setAll(getProduct());
    }

    public ObservableList<Package> getProduct(){
        ObservableList<Package> packages = FXCollections.observableArrayList();
        packages.add(new Package("bouteille de 100","b_100",10,"bouteille"));
        packages.add(new Package("bouteille de 300","b_200",10,"btl"));
        packages.add(new Package("bouteille de 400","b_300",10,"btttl"));
        packages.add(new Package("bouteille de 500","b_400",10,"boutessille"));
        packages.add(new Package("bouteille de 600","b_500",10,"s"));
        packages.add(new Package("bouteille de 700","b_600",10,"veve"));


        return packages;
    }
    //test

}
