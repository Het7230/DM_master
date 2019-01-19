package main;



import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import static javafx.stage.StageStyle.*;

public class ProgramMain extends Application {
        final static private String FXML_FILE="file:/D:/DM_Master_sources-master/sources/UI.fxml";
        final static private String SOURCES_LOCA="D:\\";
        final static private String SOURCES_URL="https://github.com/Het2002/DM_Master_sources/archive/master.zip";
        final static private String ZIP_FILE_LOCA="D:\\TEMP.ZIP";
        
        
        @Override
        public void start(Stage primaryStage) throws IOException {

            if(hasSources()==false){
                try {
                    getSources();
                    unZip();
                }catch(IOException e) {
                    e.printStackTrace();
                    System.out.println("[ERROR]无法获取资源或解压文件。");
                    trowErrorMessage();

                    Stage secondStage = new Stage();
                    Label label = new Label("拉取资源文件时出现问题，也许是这里的1网不行/这个系统有什么奇怪的毛病，如果要使用，请去"); // 放一个标签
                    StackPane secondPane = new StackPane(label);
                    Scene secondScene = new Scene(secondPane, 300, 200);
                    secondStage.setScene(secondScene);
                    secondStage.show();
                    return;
                }
            }

            FXMLLoader loader =new FXMLLoader(new URL(FXML_FILE));
            Parent root = loader.load();
            Scene scene = new Scene(root, 1007, 710);
            primaryStage.setTitle("MDmaster 初号姬");
            primaryStage.setScene(scene);


            //UICtrl controller = loader.getController(); //获取Controller的实例对象//传递primaryStage，scene参数给Controller
            //controller.setPrimaryStage(primaryStage);
            //controller.setScene(scene);



            primaryStage.show();
        }

        //程序主函数
        public static void main(String[] args) {
            launch(args);
        }

        public static void trowErrorMessage() {

        }
        
        //从gayhub上抓点好东西
        public static void getSources()throws IOException{

                URL sourcesURL=new URL(SOURCES_URL);
                HttpURLConnection connection=(HttpURLConnection)sourcesURL.openConnection();
                connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.80 Safari/537.36");
                connection.connect();
                
                InputStream stream=connection.getInputStream();
                FileOutputStream fileStream=new FileOutputStream(new File("D:\\TEMP.ZIP"));
                
                for(int i=stream.read();i!=-1;i=stream.read())
                    fileStream.write(i);
                fileStream.close();
                
                System.out.println("[INFO]资源拉取成功。");

        }
        
        //解压资源文件
        public static void unZip() throws IOException {

            File pathFile = new File(SOURCES_LOCA);
            File zipFile =new File(ZIP_FILE_LOCA);
            
            if(!pathFile.exists()) {pathFile.mkdirs();}

            //解决zip文件中有中文目录或者中文文件
            ZipFile zip = new ZipFile(zipFile, Charset.forName("GBK"));
            for(Enumeration entries = zip.entries(); entries.hasMoreElements();){
              ZipEntry entry = (ZipEntry)entries.nextElement();
              String zipEntryName = entry.getName();
              InputStream in = zip.getInputStream(entry);
              String outPath = (SOURCES_LOCA+zipEntryName).replaceAll("\\*", "/");;

              //判断路径是否存在,不存在则创建文件路径
              File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
              if(!file.exists()) {file.mkdirs();}

              //判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
              if(new File(outPath).isDirectory()) { continue; }

              //输出文件路径信息
              System.out.println("[INFO]文件："+outPath);
              OutputStream out = new FileOutputStream(outPath);
              byte[] buf1 = new byte[1024];
              int len;
              while((len=in.read(buf1))>0)
                out.write(buf1,0,len);

              in.close();
              out.close();
            }
            System.out.println("[INFO]资源解压完毕。");
                
        }



        //判断有没有资源文件，有则告诉main不需再下载资源文件(返回true)
        public static boolean hasSources() {
            
            File UIsources=new File("D:\\DM_Master_sources-master\\sources\\UI.fxml");
            
            if(UIsources.exists()==false) {
                System.out.println("[INFO]没有UI文件，获取资源并解压。");
                return false;
                }
            else {
                System.out.println("[INFO]有UI文件，直接运行。");
                return true;
                }
        }
        
    }

