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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import sample.objects.CurrentUser;
import sample.objects.DatabaseHelper;
import sample.objects.Ingredient;
import sample.objects.PackageEntry;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Ingredients implements Initializable {

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
    private TableView<Ingredient> table1;

    @FXML
    private TableColumn<Ingredient, String> ingredient_idColumn;

    @FXML
    private TableColumn<Ingredient, String> descriptionColumn;

    @FXML
    private TableColumn<Ingredient, String> categorieColumn;

    @FXML
    private TableColumn<Ingredient, Integer> stockColumn;

    @FXML
    private TextField matiereId;

    @FXML
    private TextField description;

    @FXML
    private ChoiceBox<String> choice;

    @FXML
    private Button add;

    @FXML
    private TableView<PackageEntry> table2;

    @FXML
    private TableColumn<PackageEntry, String> dateColumn;

    @FXML
    private TableColumn<PackageEntry, String> registererColumn;

    @FXML
    private TableColumn<PackageEntry, String> actionColumn;

    @FXML
    private TableColumn<PackageEntry, String> articlesColumn;

    @FXML
    private Button newEntry_btn;

    ObservableList<Ingredient> ingredients = FXCollections.observableArrayList();
    ObservableList<PackageEntry> entries = FXCollections.observableArrayList();
    @FXML
    private HBox hbox;
    Connection con;
    Statement stmt;
    ResultSet rs = null;
    Parent root;
    Scene scene;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        con = DatabaseHelper.getConnection();
        try {
            updateList();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            updateEntries();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(!CurrentUser.isAdmin()){
            hbox.setVisible(false);
        }
        choice.getItems().add("liquide");
        choice.getItems().add("solide");

        ingredient_idColumn.setCellValueFactory(new PropertyValueFactory<>("ingredient_id"));;
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("name"));;
        categorieColumn.setCellValueFactory(new PropertyValueFactory<>("categorie"));;
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));;

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("currentTime"));
        registererColumn.setCellValueFactory(new PropertyValueFactory<>("registerer"));
        actionColumn.setCellValueFactory(new PropertyValueFactory<>("action"));
        articlesColumn.setCellValueFactory(new PropertyValueFactory<>("articles"));
    }

    @FXML
    void viewEmballages(ActionEvent event) throws IOException {
        showView("Emballages.fxml", event);

    }

    @FXML
    void viewIngredients(ActionEvent event) throws IOException {
        showView("Ingredients.fxml", event);
    }

    @FXML
    void viewNewEntry(ActionEvent event) throws IOException {

    }

    @FXML
    void viewProducts(ActionEvent event) throws IOException {
        showView("Stock.fxml", event);
    }

    @FXML
    void viewRecipes(ActionEvent event) throws IOException {
        showView("recipes.fxml", event);
    }

    //method called after buttonclick on the navigationbar at the left to get the view
    public void showView(String fxmlData, ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/sample/fxml/" + fxmlData));
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.getScene().setRoot(root);
        window.setMaximized(true);
        window.show();
    }

    public void newEntry(ActionEvent actionEvent) throws IOException {
        EntryDialog.displayDialog("i");
    }

    public void addIngredient(ActionEvent actionEvent) throws SQLException {
        String id = matiereId.getText();
        String desc = description.getText();
        String categori = choice.getValue().toString();
        Statement stmt = con.createStatement();
        String sql = "INSERT INTO ingredients (ingredient_id,description,categorie) SELECT id,descr,cat FROM (SELECT '"+id+"' as id, '"+desc+"' as descr, '"+categori+"' as cat) t WHERE NOT EXISTS (SELECT 1 FROM ingredients u WHERE u.ingredient_id = '"+id+"');";
        stmt.execute(sql);
        matiereId.clear();
        description.clear();
        updateList();

    }




    private void updateEntries() throws SQLException {
        entries.clear();
        stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("select * from entryIngredient Order by  date DESC  ");
        while(rs.next()){
            PackageEntry tmp = new PackageEntry(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
            entries.add(tmp);

        }
        table2.setItems(entries);
    }

    public void updateList() throws SQLException {
        ingredients.clear();
        stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("select * from ingredients");
        while(rs.next()){
            Ingredient tmp = new Ingredient(rs.getString(2),rs.getString(1),rs.getInt(4),rs.getString(3));
            ingredients.add(tmp);

        }
        table1.setItems(ingredients);

    }
}
