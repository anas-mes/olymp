package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.objects.DatabaseHelper;
import sample.objects.Package;


import java.io.IOException;
import java.net.URL;
import java.sql.*;
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

    @FXML
    private TextField emballageName;

    @FXML
    private TextField emballageId;

    @FXML
    private ChoiceBox<String> categorie ;

    @FXML
    private Button ajout;

    Parent root;
    ObservableList<Package> packages = FXCollections.observableArrayList();

    Connection con ;
    Statement stmt ;
    ResultSet rs = null;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        con = DatabaseHelper.getConnection();
        categorie = new ChoiceBox<>();

        try {
            stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from emballage");

        while(rs.next()){
            Package tmp = new Package(rs.getString(1),rs.getString(2),rs.getInt(4),rs.getString(3));
            packages.add(tmp);
        }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        categorie.getItems().add("Paperbord");
        categorie.getItems().add("box");
        categorie.getItems().add("Plastic");

        package_idColumn.setCellValueFactory(new PropertyValueFactory<>("package_id"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        categorieColumn.setCellValueFactory(new PropertyValueFactory<>("categorie"));

        table1.setItems(packages);

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
    EntryDialog.displayDialog(packages);


    }


    //method called after buttonclick on the navigationbar at the left to get the view
    public void showView(String fxmlData, ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/sample/fxml/"+fxmlData));
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.getScene().setRoot(root);
        window.setMaximized(true);
        window.show();
    }

    public void ajouter(ActionEvent actionEvent) throws SQLException {
        String id = emballageId.getText();
        String desc = emballageName.getText();
        String categori = categorie.getValue();
        Statement stmt = con.createStatement();
        String sql = "INSERT INTO emballage VALUES ('"+id+"','"+desc+"','"+categori+"',0);";
        stmt.execute(sql);
        emballageName.clear();
        emballageId.clear();
    }


    public ObservableList<Package> getPackages(){
        return packages;
    }
}
