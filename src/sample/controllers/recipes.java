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
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.objects.*;
import sample.objects.Package;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class recipes implements Initializable {

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
    private TableView<Fabrication> table;

    @FXML
    private TableColumn<Fabrication, String> dateColumn;

    @FXML
    private TableColumn<Fabrication, String> productColumn;

    @FXML
    private TableColumn<Fabrication, String> estimeeColumn;

    @FXML
    private TableColumn<Fabrication, String> producedColumn;

    @FXML
    private TableColumn<Fabrication, String> state;

    @FXML
    private TextField searchTextfield;

    @FXML
    private Button chercher;

    @FXML
    private Button recipeBtn;

    @FXML
    private ListView<String> recipeList;

    @FXML
    private Button fabriquer;

    @FXML
    private TextField quantityProduced;

    @FXML
    private Button confirm;

    Parent root;
    Scene scene;
    Connection con = DatabaseHelper.getConnection();
    Statement stmt ;
    ResultSet rs = null;
    ResultSet set = null;

    ObservableList<Fabrication> fabrications = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            stmt = con.createStatement();
            rs =stmt.executeQuery("Select distinct recipeName from recipes ;");
            while(rs.next()){
                recipeList.getItems().add(rs.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("currenttime"));
        productColumn.setCellValueFactory(new PropertyValueFactory<>("product"));
        estimeeColumn.setCellValueFactory(new PropertyValueFactory<>("estimated"));
        producedColumn.setCellValueFactory(new PropertyValueFactory<>("produced"));
        state.setCellValueFactory(new PropertyValueFactory<>("status"));
        try {
            loadTable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if(!CurrentUser.isAdmin()){
            recipeBtn.setVisible(false);
        }

    }

    private void loadTable() throws SQLException {
        table.getItems().clear();
        stmt = con.createStatement();
        set = stmt.executeQuery("select * from fabrication Order by  date DESC ");
        while(set.next()){
            Fabrication tmp = new Fabrication(set.getString(1),set.getString(2),set.getString(3),set.getString(4),set.getString(5));
            table.getItems().add(tmp);
        }
    }


    @FXML
    void chercherClicked(ActionEvent event) throws SQLException {
        recipeList.getItems().clear();
        stmt = con.createStatement();
        if(searchTextfield.getText().trim().isEmpty()){

            rs =stmt.executeQuery("Select distinct recipeName from recipes ;");
            while(rs.next()){
                recipeList.getItems().add(rs.getString(1));
            }

        }else{
            rs = stmt.executeQuery("select distinct recipeName from recipes where recipeName like '%" + searchTextfield.getText() + "%';");
            while (rs.next()) {
                recipeList.getItems().add(rs.getString(1));
            }
        }
    }


    @FXML
    void fabriquerClicked(ActionEvent event) throws IOException, SQLException {
        if(!(recipeList.getSelectionModel().getSelectedItem()== null))
        FabricationDialog.displayDialog(recipeList.getSelectionModel().getSelectedItem());

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

    public void addRecipe(ActionEvent actionEvent) throws IOException {
        AddRecipe.displayRecipeDialog();
    }

    public void confirmation(ActionEvent actionEvent) throws SQLException {
        stmt = con.createStatement();

        Fabrication tmp = table.getSelectionModel().getSelectedItem();
        double quant = Double.parseDouble(quantityProduced.getText());
        if(tmp.getStatus().equals("ouvert")){
            stmt.execute("update fabrication set quantityProduced="+quant+" , status = 'fermé' where date ='"+tmp.getCurrenttime()+"' ;");

        }
        loadTable();


    }

    public void logout(ActionEvent event) throws IOException {
        showView("Login.fxml",event);
    }
}
