package main;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

    public class ProgramMain extends Application {
        final String FXML_FILE="file:/D:/DM_master/sources/UI.fxml";
        final static String SOURCES_URL="https://github.com/Het2002/DM_Master_sources/archive/master.zip";
        @Override
        public void start(Stage primaryStage) throws IOException {
            Parent root = FXMLLoader.load(new URL(FXML_FILE));
            Scene scene = new Scene(root, 1007, 710);
            primaryStage.setTitle("MDmaster 初号姬");
            primaryStage.setScene(scene);

            primaryStage.show();
        }

        public static void main(String[] args) {
            //getSources();
            launch(args);
        }

        public static void getSources(){
            try {
                URL sourcesURL=new URL(SOURCES_URL);
                
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

