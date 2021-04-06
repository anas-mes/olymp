package sample.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.objects.CurrentUser;
import sample.objects.DatabaseHelper;
import sample.objects.Ingredient;
import sample.objects.Package;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class EntryDialog implements Initializable{

    private static String ss;
    ObservableList<Package> packages = FXCollections.observableArrayList();
    ObservableList<Ingredient> ingredients = FXCollections.observableArrayList();

    @FXML
    private ChoiceBox<String> choice = new ChoiceBox<>();
    @FXML
    private static Label description ;

    @FXML
    private Spinner quantity;

    @FXML
    private Button add;



    static Stage window = new Stage();
    Connection con = DatabaseHelper.getConnection();
    static Statement stmt=null;
    static ResultSet rs = null ;




    @FXML
    void addPackage(ActionEvent event) throws SQLException {
        updateStock();
       updateEntry();
       window.close();
    }

    private boolean updateStock() throws SQLException {

        if(ss.equals("p")){
            stmt.execute("Update emballage set stock = stock + "+quantity.getValue().toString()+" where emballage_id = '"+choice.getValue()+"';");
        return true ;
        }else if (ss.equals("i")){
            stmt.execute("Update ingredients set stock = stock + "+quantity.getValue().toString()+" where ingredient_id = '"+choice.getValue()+"';");
        return true ;}

        return false;
    }

    private void updateEntry() throws SQLException {
        String id = Integer.toString(CurrentUser.getUser_id());
        if (ss.equals("p")) {
            stmt.execute("insert into entryPackage value (current_timestamp,'" + id + "','Checkin','" + choice.getValue() + "');");
        } else if (ss.equals("i")) {
            stmt.execute("insert into entryIngredient value (current_timestamp,'" + id + "','Checkin','" + choice.getValue() + "');");
        }
    }


    public static void displayDialog(String i) throws IOException {
       ss=i;
        Parent root = FXMLLoader.load(EntryDialog.class.getResource("/sample/fxml/EntryDialog.fxml"));
        window.setScene(new Scene(root));
        window.setTitle("add");
        window.show();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(ss.equals("p")) {
            try {
                stmt = con.createStatement();
                rs = stmt.executeQuery("select * from emballage");

                while (rs.next()) {
                    Package tmp = new Package(rs.getString(2), rs.getString(1), rs.getInt(4), rs.getString(3));
                    packages.add(tmp);
                    choice.getItems().add(rs.getString(1));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else{
            try {
                stmt = con.createStatement();
                rs = stmt.executeQuery("select * from ingredients");

                while (rs.next()) {
                    Ingredient tmp = new Ingredient(rs.getString(2), rs.getString(1), rs.getInt(4), rs.getString(3));
                    ingredients.add(tmp);
                    choice.getItems().add(rs.getString(1));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }



        }

        SpinnerValueFactory<Integer> quantityvaluefactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10000,0);
        this.quantity.setValueFactory(quantityvaluefactory);
        quantity.setEditable(true);



       /* choice.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                    description.setText(new_val.toString());
                });*/
    }


    public void labelchanger(ActionEvent actionEvent) {
        String c ;
        choice.getSelectionModel().selectedIndexProperty().addListener((v,oldvalue, newvalue) -> {
            for(Package pk : packages){
                if(pk.getPackage_id().equals(newvalue)){
                    description.setText(pk.getName());
                }
            }

        });

    }

}











