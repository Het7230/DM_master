package main;


import java.io.IOException;
import java.util.Properties;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

    public class ProgramMain extends Application {
        @Override
        public void start(Stage primaryStage) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("D:/DM_master/sources/UI.fxml"));
            Scene scene = new Scene(root, 1007, 710);
            primaryStage.setTitle("MDmaster 初号姬");
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.UNDECORATED);

            primaryStage.show();
        }

        public static void main(String[] args) {
            getSources();
            launch(args);
        }

        public static void getSources(){

        }
    }

