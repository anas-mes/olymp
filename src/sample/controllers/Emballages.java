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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import sample.objects.CurrentUser;
import sample.objects.DatabaseHelper;
import sample.objects.Package;
import sample.objects.PackageEntry;


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
    private Button newEntry;

    @FXML
    private TextField emballageName;

    @FXML
    private TextField emballageId;


    @FXML
    private ChoiceBox<String> choice;

    @FXML
    private Label title;

    @FXML
    private Button ajout;

    @FXML
    private HBox hbox;


    Parent root;
    ObservableList<Package> packages = FXCollections.observableArrayList();
    ObservableList<PackageEntry> entries = FXCollections.observableArrayList();



    Connection con ;
    Statement stmt ;
    ResultSet rs = null;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
        choice.getItems().add("categorie 1");
        choice.getItems().add("categorie 2");
        choice.getItems().add("categorie 3");

        if(!CurrentUser.isAdmin()){
            hbox.setVisible(false);
        }

        package_idColumn.setCellValueFactory(new PropertyValueFactory<>("package_id"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        categorieColumn.setCellValueFactory(new PropertyValueFactory<>("categorie"));

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("currentTime"));
        registererColumn.setCellValueFactory(new PropertyValueFactory<>("registerer"));
        actionColumn.setCellValueFactory(new PropertyValueFactory<>("action"));
        articlesColumn.setCellValueFactory(new PropertyValueFactory<>("articles"));

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
    EntryDialog.displayDialog("p");


    }


    //method called after buttonclick on the navigationbar at the left to get the view
    public void showView(String fxmlData, ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/sample/fxml/"+fxmlData));
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.getScene().setRoot(root);
        window.setMaximized(true);
        window.show();
    }
    //add a new package item to the list
    public void ajouter(ActionEvent actionEvent) throws SQLException {
        String id = emballageId.getText();
        String desc = emballageName.getText();
        String categori = choice.getValue().toString();
        Statement stmt = con.createStatement();
        String sql = "INSERT INTO emballage (emballage_id,description,categorie) SELECT id,descr,cat FROM (SELECT '"+id+"' as id, '"+desc+"' as descr, '"+categori+"' as cat) t WHERE NOT EXISTS (SELECT 1 FROM emballage u WHERE u.emballage_id = '"+id+"');";
        stmt.execute(sql);
        emballageName.clear();
        emballageId.clear();
        updateList();
    }

    public void updateList() throws SQLException {
        packages.clear();
        stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("select * from emballage");
        while(rs.next()){
            Package tmp = new Package(rs.getString(2),rs.getString(1),rs.getInt(4),rs.getString(3));
            packages.add(tmp);

    }
        table1.setItems(packages);

    }


    public void updateEntries() throws SQLException {
        entries.clear();
        stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("select * from entryPackage Order by  date DESC  ");
        while(rs.next()){
            PackageEntry tmp = new PackageEntry(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
            entries.add(tmp);

        }
        table2.setItems(entries);

    }




    public ObservableList<Package> getPackages(){
        return packages;
    }
}
