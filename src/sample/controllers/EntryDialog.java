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
import sample.objects.DatabaseHelper;
import sample.objects.Package;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class EntryDialog implements Initializable{

    ObservableList<Package> packages = FXCollections.observableArrayList();

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
       String id = null;
       String tochange= choice.getValue();
       int stockupdate =0;
       for(Package pk : packages){
           if(tochange.equals(pk.getPackage_id())){
               stockupdate = quantity.getValue().hashCode() + pk.getStock();
               id = pk.getPackage_id();break;
           }
       }
       // System.out.println("id ="+id+" add = ");
    stmt.execute("Update emballage set stock ="+stockupdate+" where emballage_id = '"+id+"';");
       window.close();
    }



    public static void displayDialog() throws IOException {


        Parent root = FXMLLoader.load(EntryDialog.class.getResource("/sample/fxml/EntryDialog.fxml"));
        window.setScene(new Scene(root));
        window.setTitle("add");
        window.show();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            stmt=con.createStatement();
            rs=stmt.executeQuery("select * from emballage");

            while(rs.next()){
                Package tmp = new Package(rs.getString(2),rs.getString(1),rs.getInt(4),rs.getString(3));
                packages.add(tmp);
                choice.getItems().add(rs.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        SpinnerValueFactory<Integer> quantityvaluefactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10000,0);
        this.quantity.setValueFactory(quantityvaluefactory);
        quantity.setEditable(true);




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











