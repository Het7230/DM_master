package main;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
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

        //程序主函数
        public static void main(String[] args) {
            //if(hasSources()==false){
            //  getSources();
            //  unZip();
            //}
            launch(args);
        }
        
        //从gayhub上抓点好东西
        public static void getSources(){
            try {
                URL sourcesURL=new URL(SOURCES_URL);
                HttpURLConnection connection=(HttpURLConnection)sourcesURL.openConnection();
                connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.80 Safari/537.36");
                connection.connect();
                
                InputStream stream=connection.getInputStream();
                FileOutputStream fileStream=new FileOutputStream(new File("D:\\TEMP.ZIP"));
                
                for(int i=stream.read();i!=-1;i=stream.read())
                    fileStream.write(i);
                fileStream.close();
                
                System.out.println("OK!");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        //解压资源文件
        public static void unZip() {
            
        }
        
        //判断有没有资源文件，有则告诉main不需再下载资源文件(返回true)
        public static boolean hasSources() {
            
            return true;
        }
        
    }

