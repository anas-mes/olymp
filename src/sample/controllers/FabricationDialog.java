package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.objects.Ingredient;
import sample.objects.Package;
import sample.objects.Recette;
import sample.objects.RecipeEntry;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FabricationDialog implements Initializable{

    private static Stage window = new Stage();
    @FXML
    private TableView<RecipeEntry> table;
    @FXML
    private TableColumn<RecipeEntry,String> ingredient;

    @FXML
    private TableColumn<RecipeEntry, Integer> purcentage;

    @FXML
    private TableColumn<RecipeEntry, Integer> gramm;

    @FXML
    private TableColumn<RecipeEntry, Integer> litre;

    @FXML
    static private TextField batchSize = new TextField();

    @FXML
    private Button calculer_btn;

    @FXML
    private Button fabriquer_btn;

    static Recette tmp = new Recette();

    @FXML
    void calculer(ActionEvent event) {


    }


    @FXML
    void fabriquer(ActionEvent event) {

    }

    public static void displayDialog(Recette recette) throws IOException {
        for (Ingredient key : recette.getIngredients().keySet()){
            System.out.println(key);
            System.out.println(recette.getIngredients().get(key));
        }
        tmp = recette;
        Parent root = FXMLLoader.load(EntryDialog.class.getResource("/sample/fxml/FabricationDialog.fxml"));
        //window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(recette.getName());
        window.setScene(new Scene(root));
        window.showAndWait();


    }

    @Override
   public void initialize(URL location, ResourceBundle resources) {
       ingredient.setCellValueFactory(new PropertyValueFactory<>("ingName"));
        purcentage.setCellValueFactory(new PropertyValueFactory<>("purcentage"));
       gramm.setCellValueFactory(new PropertyValueFactory<>("gram"));
       litre.setCellValueFactory(new PropertyValueFactory<>("ml"));

        table.setItems(getProduct());


    }

    public ObservableList<RecipeEntry> getProduct(){
        ObservableList<RecipeEntry> ingredients = FXCollections.observableArrayList();
        for ( Ingredient key : tmp.getIngredients().keySet()  ) {
            RecipeEntry entry = new RecipeEntry();
            entry.setIngName(key.getName());
            entry.setPurcentage(tmp.getIngredients().get(key));


            ingredients.add(entry);

        }


        return ingredients;
    }




    }


