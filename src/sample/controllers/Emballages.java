package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.objects.Package;


import java.io.IOException;
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

    Parent root;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        package_idColumn.setCellValueFactory(new PropertyValueFactory<>("package_id"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        categorieColumn.setCellValueFactory(new PropertyValueFactory<>("categorie"));

        table1.setItems(getProduct());
        table1.getColumns().addAll(package_idColumn,descriptionColumn,categorieColumn,stockColumn);
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

    public void viewEmballages(ActionEvent actionEvent) throws IOException {
        showView("Emballages.fxml",actionEvent);

    }

    public void viewIngredients(ActionEvent actionEvent) throws IOException {
        showView("Ingredients.fxml",actionEvent);
    }

    public void viewRecipes(ActionEvent actionEvent) throws IOException {
        showView("recipes.fxml",actionEvent);
    }

    public void viewProducts(ActionEvent actionEvent) throws IOException {
        showView("Stock.fxml",actionEvent);
    }

    public void viewNewEntry(ActionEvent actionEvent) throws IOException {
    EntryDialog.displayDialog();
    }


    //method called after buttonclick on the navigationbar at the left to get the view
    public void showView(String fxmlData, ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/sample/fxml/"+fxmlData));
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.getScene().setRoot(root);
        window.setMaximized(true);
        window.show();
    }

}
