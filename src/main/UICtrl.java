package main;

import com.jfoenix.controls.*;
import javafx.animation.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;


public class UICtrl {

    List<String> ignoreList=new ArrayList<>();

    int chosenTime=120;
    int times=0;
    int already=0;
    int singleCycle=0;
    int showWhich=1;

    String chosenName;

    boolean cycleEnd =true;
    boolean ignoreTinmesOut=false;
    boolean ignorePast=false;

    AnimationTimer timer =new AnimationTimer() {
        @Override
        public void handle(long now) {
/*
            try{
                Thread.sleep(speed);
            }catch (Exception e){

            }*/

            if(already>=chosenTime){
                if(!ignoreList.contains(chosenName)||ignorePast){
                    ignoreList.add(chosenName);
                    cycleEnd=true;
                    already=0;
                    singleCycle=0;
                    ignoreTinmesOut=false;
                    stop();
                    choose.setDisable(false);
                    return;
                }else
                    ignoreTinmesOut=true;

            }
            if(singleCycle>=times&&ignoreTinmesOut==false){
                cycleEnd=true;
                singleCycle=0;
            }

            if(cycleEnd){
                times=(int)(1+Math.random()*(chosenTime-already));
                cycleEnd=false;
                showWhich=(int)(1+Math.random()*2);
            }

            switch (showWhich){
                case 1:{
                    chosenName=data.randomGet();
                    chosen_1.setText(chosenName);
                    already++;
                    singleCycle++;
                    break;
                }

                case 2:{
                    chosenName=data.randomGet();
                    chosen_2.setText(chosenName);
                    already++;
                    singleCycle++;
                    break;
                }
            }


        }
    };


    public Boolean isNameChoose=true;
    public short speed;

    @FXML
    public Label chosen_1;
    public Label chosen_2;

    public JFXListView nameList;
    public JFXChipView a;


    public JFXButton goBack;
    public JFXButton choose;

    public JFXCheckBox numbChoose;
    public JFXCheckBox nameChoose;

    public JFXCheckBox chooseOnce;
    public JFXCheckBox ignoreOnce;

    public JFXCheckBox randomTimes;
    public JFXCheckBox fixedTimes;

    public JFXSlider chooseTimes;
    public JFXSlider speedBar;
    public JFXTextArea inputName;

    public Pane numbPane;
    public Pane namePane;

    public Stage stage;
    public Scene scene;

    public Pane mainPane;

    private Data data=new Data();

    public static final ObservableList names = FXCollections.observableArrayList();



    public void setStage(Stage stage){
        this.stage = stage;
    }
    public void setScene(Scene scene){
        this.scene = scene;
    }
    public void setdata(Data data){
    }

    @FXML
    void anPai(){
        choose.setDisable(true);
        //speed=(short) speedBar.getValue();
        timer.start();
    }



    //两种选择方式的切换，没什么好说的。
    @FXML
    void numbChoose_selected(){
        isNameChoose=false;
        numbChoose.setSelected(true);
        namePane.setVisible(false);
        nameChoose.setSelected(false);
        numbPane.setVisible(true);
    }

    @FXML
    void nameChoose_selected(){
        isNameChoose=true;
        nameChoose.setSelected(true);
        numbPane.setVisible(false);
        numbChoose.setSelected(false);
        namePane.setVisible(true);
    }
    @FXML
    void addName(){
        data=new Data();
        data.add(inputName.getText());

        names.clear();
        names.addAll(data.getAll());
        nameList.setItems(names);
        nameList.refresh();

        data.saveToFile();
        inputName.setText("");
    }

    @FXML
    void deleteName(){
        data.delete((String)nameList.getSelectionModel().getSelectedItems().get(0));
        names.remove((String)nameList.getSelectionModel().getSelectedItems().get(0));
        data.saveToFile();
    }

    @FXML
    void showNameManger(){
        names.clear();
        names.addAll(data.getAll());
        nameList.setItems(names);

        final Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(true);
        final KeyValue kv = new KeyValue(namePane.layoutXProperty(), 0);
        final KeyFrame kf = new KeyFrame(Duration.millis(500), kv);


        final KeyValue kv2 = new KeyValue(mainPane.layoutXProperty(), mainPane.getWidth()/2);
        final KeyFrame kf2 = new KeyFrame(Duration.millis(400), kv2);

        timeline.getKeyFrames().add(kf);
        timeline.getKeyFrames().add(kf2);

        timeline.play();


    }


    @FXML
    void deleteAllName(){

    }

    @FXML
    void ignoreOnce_selected(ActionEvent event) {

    }

    @FXML
    void chooseOnce_selected(ActionEvent event) {

    }


    @FXML
    void randomTimes_selected(ActionEvent event) {

    }


    @FXML
    void fixedTimes_selected(ActionEvent event) {

    }


    @FXML
    void nameChoose_selected(ActionEvent event) {

    }

    @FXML
    void numbChoose_selected(ActionEvent event) {

    }
}
