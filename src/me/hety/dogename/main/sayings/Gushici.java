package me.hety.dogename.main.sayings;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import me.hety.dogename.main.Common;
import me.hety.dogename.main.DialogMaker;
import me.hety.dogename.main.controllers.GushiciPaneController;

public class Gushici {

    private final String GUSHICI_API="https://v1.jinrishici.com/all.json";

    String gushiciJSON = null;

    private String getGushici() {
        return Common.getHtml(GUSHICI_API,false);
    }

    public void showGushici(Pane rootPane, Label topBar){

        new Thread(()->{

            //gushiciJSON=getGushici();

            String content, title, author, type;

            if((gushiciJSON=getGushici())!=null){
                GushiciData gushiciData=new Gson().fromJson(gushiciJSON,GushiciData.class);
                content=gushiciData.getContent();
                title=gushiciData.getTitle();
                author=gushiciData.getAuthor();
                type=gushiciData.getType();
                Platform.runLater(()->{
                    topBar.setText(content+" ——"+author+"《"+title+"》");
                    GushiciPaneController gushiciPaneController=new GushiciPaneController(content, title, author, type);
                    new DialogMaker(rootPane).creatDialogWithOneBtn("每日古诗词",gushiciPaneController);
                });
            }
        }).start();

    }

    static class GushiciData{

        private String content;//诗歌的内容

        @SerializedName("origin")
        private String title;//诗歌的标题

        private String author;//诗歌作者

        @SerializedName("category")
        private String type;//诗歌类型

        public void setContent(String content) {
            this.content = content;
        }
        public String getContent() {
            return content;
        }

        public void setTitle(String title) {
            this.title = title;
        }
        public String getTitle() {
            return title;
        }

        public void setAuthor(String author) {
            this.author = author;
        }
        public String getAuthor() {
            return author;
        }

        public void setType(String type) {
            this.type = type;
        }
        public String getType() {
            return type;
        }
    }
}
