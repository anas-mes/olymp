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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.objects.DatabaseHelper;
import sample.objects.Package;
import sample.objects.PackageEntry;
import sample.objects.stock;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Stock implements Initializable {

    @FXML
    private BorderPane borderpane;

    @FXML
    private Button emballage_btn;

    @FXML
    private Button ingredients_btn;

    @FXML
    private Button recipe_btn;

    @FXML
    private Button products_btm;

    @FXML
    private Button deconnect;

    @FXML
    private TableView<stock> table1;

    @FXML
    private TableColumn<stock, String> product_idColumn;

    @FXML
    private TableColumn<stock, String> descriptionColumn;

    @FXML
    private TableColumn<stock, String> categorieColumn;

    @FXML
    private TableColumn<stock, Integer> stockColumn;

    @FXML
    private TableView<?> table2;

    @FXML
    private TableColumn<?, ?> dateColumn;

    @FXML
    private TableColumn<?, ?> enregistreparColumn;

    @FXML
    private TableColumn<?, ?> actionColumn;

    @FXML
    private TableColumn<?, ?> articlesColumn;

    Parent root;
    Scene scene;

    Connection con = DatabaseHelper.getConnection();
    Statement stmt ;

    ObservableList<stock> products = FXCollections.observableArrayList();
    ObservableList<stock> checkouts = FXCollections.observableArrayList();

    @FXML
    void deconnectClicked(ActionEvent event) {

    }
    @FXML
    void viewEmballages(ActionEvent event) throws IOException {
        showView("Emballages.fxml",event);

    }

    @FXML
    void viewIngredients(ActionEvent event) throws IOException {
        showView("Ingredients.fxml",event);

    }

    @FXML
    void viewProducts(ActionEvent event) throws IOException {
        showView("Stock.fxml",event);

    }

    @FXML
    void viewRecipes(ActionEvent event) throws IOException {
        showView("recipes.fxml",event);

    }



    public void showView(String fxmlData, ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/sample/fxml/"+fxmlData));
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.getScene().setRoot(root);
        window.setMaximized(true);
        window.show();
    }

    public void logout(ActionEvent event) throws IOException {
        showView("Login.fxml",event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        con = DatabaseHelper.getConnection();

        try {
            updateList();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        product_idColumn.setCellValueFactory(new PropertyValueFactory<>("product_id"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        categorieColumn.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));


    }


    private void updateList() throws SQLException {
        products.clear();
        stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("select * from stock");
        while(rs.next()){
            stock tmp = new stock(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4));
            products.add(tmp);

        }
        table1.setItems(products);
    }
}
