package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import sample.objects.Admin;
import sample.objects.Employee;


public class Main extends Application  {
    Stage window;
    Parent root;
    static int i =0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
       window = primaryStage;
       root = FXMLLoader.load(getClass().getResource("/sample/test.fxml"));
       window.setScene(new Scene(root,1100,700));
       window.show();

    }



    }


