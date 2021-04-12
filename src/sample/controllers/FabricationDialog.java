package sample.controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.objects.*;
import sample.objects.Package;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

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
    private TextField quantity;


    @FXML
    private Button calculer_btn;

    @FXML
    private Button fabriquer_btn;

    static Connection con = DatabaseHelper.getConnection();
    static Statement stmt ;
    static ResultSet rs = null;
    static String r = null;
    static ObservableList<RecipeEntry> ingredients = FXCollections.observableArrayList();

    @FXML
    void calculer(ActionEvent event) {
        double batch = Double.parseDouble(quantity.getText());
        for(RecipeEntry ig: ingredients){
            ig.setGram(ig.calculateGram(batch));
        }

        table.refresh();

    }


    @FXML
    void fabriquer(ActionEvent event) throws SQLException {
        stmt = con.createStatement();
        stmt.execute("insert into fabrication(date,product,quantityEstimated) value (current_timestamp ,'"+r+"',"+quantity.getText()+")");
        for(RecipeEntry re : ingredients){
            stmt.execute("update ingredients set stock=stock-"+re.getGram()+" where ingredient_id='"+re.getIngName()+"'; ");

        }


        closeProgram();
    }

    public static void displayDialog(String recipe) throws IOException, SQLException {
        r= recipe;
        stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT ingredientId, purcentage from recipes where recipeName='"+recipe+"';");
        while (rs.next()){
            RecipeEntry tmp = new RecipeEntry(rs.getString(1),rs.getDouble(2),0.0,0.0);
            ingredients.add(tmp);
        }
        Parent root = FXMLLoader.load(EntryDialog.class.getResource("/sample/fxml/FabricationDialog.fxml"));
        window.setTitle(recipe);
        window.setScene(new Scene(root));
        window.show();

    }

    @Override
   public void initialize(URL location, ResourceBundle resources) {
       ingredient.setCellValueFactory(new PropertyValueFactory<>("ingName"));
        purcentage.setCellValueFactory(new PropertyValueFactory<>("purcentage"));
       gramm.setCellValueFactory(new PropertyValueFactory<>("gram"));

       table.setItems(ingredients);

        Pattern pattern = Pattern.compile("\\d*|\\d+\\.\\d*");
        TextFormatter formatter = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        });

        quantity.setTextFormatter(formatter);

       window.setOnCloseRequest(e -> {e.consume(); closeProgram();});


    }

    private void closeProgram() {
        ingredients.clear();
        window.close();

    }



}


