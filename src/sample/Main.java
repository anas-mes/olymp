package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


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
       root = FXMLLoader.load(getClass().getResource("/sample/fxml/Login.fxml"));
       window.setScene(new Scene(root));
       window.setTitle("Olympe Beauty");
       window.setMaximized(true);
       window.show();

    }



    }


