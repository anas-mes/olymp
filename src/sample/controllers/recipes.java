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
import sample.objects.Ingredient;
import sample.objects.Package;
import sample.objects.Recette;

import java.io.IOException;
import java.net.URL;
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
    private TableView<?> table;

    @FXML
    private TableColumn<?, ?> dateColumn;

    @FXML
    private TableColumn<?, ?> productColumn;

    @FXML
    private TableColumn<?, ?> estimeeColumn;

    @FXML
    private TableColumn<?, ?> producedColumn;

    @FXML
    private TableColumn<?, ?> state;

    @FXML
    private TextField searchTextfield;

    @FXML
    private Button chercher;

    @FXML
    private ListView<Recette> recipeList;

    @FXML
    private Button fabriquer;

    Parent root;
    Scene scene;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        recipeList.getItems().addAll(getProduct());
       


    }

    public ObservableList<Recette> getProduct(){
        Ingredient ing = new Ingredient("acide acidic","ac_124");
        Ingredient ing2 = new Ingredient("eau","o_757");
        Ingredient ing3 = new Ingredient("silf","1vr18");
        Ingredient ing4 = new Ingredient("bacccec","848ec8e");
        Recette example = new Recette("gel","123");

        example.addIngredient(ing,22);
        example.addIngredient(ing2,35);
        example.addIngredient(ing3,25);
        example.addIngredient(ing4,15);

        ObservableList<Recette> recettes = FXCollections.observableArrayList();
        recettes.add(example);
        return recettes;
    }


    @FXML
    void chercherClicked(ActionEvent event) {

    }

    @FXML
    void deconnection(ActionEvent event) {

    }

    @FXML
    void fabriquerClicked(ActionEvent event) throws IOException {
        getProduct();
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

}
