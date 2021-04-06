package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.objects.DatabaseHelper;
import sample.objects.Package;
import sample.objects.RecipeIngredients;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class AddRecipe implements Initializable {

    @FXML
    private TableView<RecipeIngredients> recetteTable;

    @FXML
    private TableColumn<RecipeIngredients, String> ingredientColumn;

    @FXML
    private TableColumn<RecipeIngredients, Double> purcentageColumn;

    @FXML
    private ChoiceBox<String> ingredientChoice;

    @FXML
    private Spinner<Double> purcentage;

    @FXML
    private Button ingredientBtn;

    @FXML
    private TextField recipeName;

    @FXML
    private TextField recipeId;

    @FXML
    private Button recipeBtn;

    private static DecimalFormat df2 = new DecimalFormat("#.##");

    ObservableList<RecipeIngredients> ingredients = FXCollections.observableArrayList();

    Connection con = DatabaseHelper.getConnection();
    Statement stmt ;
    ResultSet rs = null;

    static Stage window = new Stage();

    @FXML
    void addIngredient(ActionEvent event) {
        boolean  exists = false;
        for(RecipeIngredients ri : ingredients){
            if(ri.getIngredient().equals(ingredientChoice.getValue())){
                exists = true;
            }
        }
        RecipeIngredients tmp = new RecipeIngredients(ingredientChoice.getValue(),purcentage.getValue());
        if(!exists){
        ingredients.add(tmp);
        recetteTable.setItems(ingredients);}
    }

    @FXML
    void addRecipe(ActionEvent event) throws SQLException {
        if(!recipeName.getText().trim().isEmpty() && !recipeId.getText().trim().isEmpty() ){
            for(RecipeIngredients ri : ingredients){
               stmt.execute("Insert into recipes value('"+recipeName.getText()+"','"+recipeId.getText()+"','"+ri.getIngredient()+"','"+ri.getPurcentage()+"');");
            }
            window.close();
        }else{
            System.out.println("nothing");
        }

    }

    public static void displayRecipeDialog() throws IOException {

        Parent root = FXMLLoader.load(EntryDialog.class.getResource("/sample/fxml/AddRecipe.fxml"));
        window.setScene(new Scene(root));
        window.setTitle("Ajouter recette");
        window.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ingredientColumn.setCellValueFactory(new PropertyValueFactory<>("ingredient"));
        purcentageColumn.setCellValueFactory(new PropertyValueFactory<>("purcentage"));

        try {
            stmt = con.createStatement();
            rs =stmt.executeQuery("select ingredient_id from ingredients;");
            while(rs.next()){
            ingredientChoice.getItems().add(rs.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        SpinnerValueFactory<Double> purcentagecaluefactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.00,99.99,0.00);
        this.purcentage.setValueFactory(purcentagecaluefactory);
        purcentage.setEditable(true);

    }
}