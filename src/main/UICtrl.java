package main;

import com.jfoenix.controls.*;
import javafx.animation.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class UICtrl {

    boolean taoluMode;


    List<String> ignoreNameList=new ArrayList<>();
    short ignoreNameTimes=0;

    List<String> ignoreNumberList=new ArrayList<>();
    short ignoreNumberTimes=0;

    public JFXTextField minNumb;
    public JFXTextField maxNumb;

    public short minNumber;
    public short maxNumber;


    int chosenTime=120;
    int times=0;
    int already=0;
    int singleCycle=0;
    int showWhich=1;

    String chosenName;

    boolean cycleEnd =true;
    boolean ignoreTimesOut=false;
    boolean ignorePast=true;

    boolean equalMode=true;
    
    boolean forceStop =false;

    File nameIgnoreFile =new File("D:\\DM_Master_sources-master\\nameIgnoreList");
    File numbIgnoreFile =new File("D:\\DM_Master_sources-master\\numbIgnoreList");

    void clearIgnoreList(){
        if(isNameChoose)
            ignoreNameList=new ArrayList<>();
        else
            ignoreNumberList=new ArrayList<>();
    }
	
	void clearTaoluList(){
        data.clearTaoluedName();
	}

	void showTaoluMode(){
        showInfoDialog("","");
    }

    void showEqualMode(){
        showInfoDialog("","");
    }

    public void readIgnoreList(){

        try{

            if(nameIgnoreFile.exists()!=true){
                nameIgnoreFile.createNewFile();
                ignoreNameList= new ArrayList<>();
                return;
            }

            ObjectInputStream ois =new ObjectInputStream(new FileInputStream(nameIgnoreFile));
            this.ignoreNameList=(ArrayList)ois.readObject();

            ignoreNameTimes=(short) ignoreNameList.size();

        }catch (Exception e){
            ignoreNameList=new ArrayList<>();
            e.printStackTrace();
        }

        try{

            if(numbIgnoreFile.exists()!=true){
                numbIgnoreFile.createNewFile();
                ignoreNumberList= new ArrayList<>();
                return;
            }

            ObjectInputStream ois =new ObjectInputStream(new FileInputStream(nameIgnoreFile));
            this.ignoreNumberList=(ArrayList)ois.readObject();

            ignoreNumberTimes=(short) ignoreNumberList.size();

        }catch (Exception e){
            ignoreNumberList=new ArrayList<>();
            e.printStackTrace();
        }
    }

    void writeIgnoreList(){
        try{
            ObjectOutputStream oos =new ObjectOutputStream(new FileOutputStream(nameIgnoreFile));
            oos.writeObject(ignoreNameList);
            oos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            ObjectOutputStream oos =new ObjectOutputStream(new FileOutputStream(numbIgnoreFile));
            oos.writeObject(ignoreNumberList);
            oos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
//-------------------------------------------------------------------------------------------
    AnimationTimer timer =new AnimationTimer() {
        @Override
        public void handle(long now) {
            
            if(forceStop){
                already=chosenTime+1;
                isRunning=false;
                forceStop=false;
            }
            
            try{
                Thread.sleep(speed);
            }catch (Exception e){ }

            if(already>=chosenTime){
                if(!ignoreNameList.contains(chosenName)||!ignorePast){
                    if(ignorePast)
                        ignoreNameList.add(chosenName);
                    if(equalMode)
                        writeIgnoreList();
                    cycleEnd=true;
                    already=0;
                    singleCycle=0;
                    ignoreTimesOut=false;
                    ignoreNameTimes++;

                    switch (showWhich){
                        case 1:{
                            if(chosen_2.getText().contains("→"))
                                chosen_2.setText(chosen_2.getText().replace("→",""));

                            chosen_1.setText("→"+chosen_1.getText());

                            if(taoluMode)
                                data.addTaoluedName(chosen_1.getText().replace("→",""),5);System.out.println(chosen_1.getText()+"已安排5次");

                            break;
                        }
                        case 2:{
                            if(chosen_1.getText().contains("→"))
                                chosen_1.setText(chosen_1.getText().replace("→",""));

                            chosen_2.setText("→"+chosen_2.getText());

                            if(taoluMode)
                                data.addTaoluedName(chosen_2.getText().replace("→",""),4);System.out.println(chosen_2.getText()+"已安排4次");

                            break;
                        }
                    }
                    isRunning=false;
                    choose.setText("安排一下");
                    stop();
                    controllerPane.setDisable(false);
                    return;
                }else
                    ignoreTimesOut=true;



            }
            if(singleCycle>=times&&!ignoreTimesOut){
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
                    chosenName=data.randomGet(taoluMode);
                    chosen_1.setText(chosenName);
                    already++;
                    singleCycle++;
                    break;
                }

                case 2:{
                    chosenName=data.randomGet(taoluMode);
                    chosen_2.setText(chosenName);
                    already++;
                    singleCycle++;
                    break;
                }
            }


        }
    };
//---------------------------------------------------------------------------------------
    AnimationTimer numbTimer =new AnimationTimer() {
        @Override
        public void handle(long now) {


            if(forceStop){
                already=chosenTime+1;
                isRunning=false;
                forceStop=false;
            }

            try{
                Thread.sleep(speed);
            }catch (Exception e){ }

            if(already>=chosenTime){
                if(!ignoreNumberList.contains(chosenName)||!ignorePast){
                    if(ignorePast)
                        ignoreNumberList.add(chosenName);
                    if(equalMode)
                        writeIgnoreList();
                    cycleEnd=true;
                    already=0;
                    singleCycle=0;
                    ignoreTimesOut=false;
                    ignoreNameTimes++;

                    switch (showWhich){
                        case 1:{
                            if(chosen_2.getText().contains("→"))
                                chosen_2.setText(chosen_2.getText().replace("→",""));

                            chosen_1.setText("→"+chosen_1.getText());

                            break;
                        }
                        case 2:{
                            if(chosen_1.getText().contains("→"))
                                chosen_1.setText(chosen_1.getText().replace("→",""));

                            chosen_2.setText("→"+chosen_2.getText());

                            break;
                        }
                    }
                    isRunning=false;
                    choose.setText("安排一下");
                    stop();
                    controllerPane.setDisable(false);
                    return;
                }else
                    ignoreTimesOut=true;

            }
            if(singleCycle>=times&&!ignoreTimesOut){
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
                    chosen_1.setText(String.valueOf(
                            (int)minNumber+(int)(Math.random()*(maxNumber-minNumber))
                    ));
                    chosenName=chosen_1.getText();
                    already++;
                    singleCycle++;
                    break;
                }

                case 2:{
                    chosen_2.setText(String.valueOf(
                            (int)minNumber+(int)(Math.random()*(maxNumber-minNumber))
                    ));
                    chosenName=chosen_2.getText();
                    already++;
                    singleCycle++;
                    break;
                }
            }


        }
    };



    public boolean isNameChoose=true;
    public short speed;

    @FXML
    public Label chosen_1;
    public Label chosen_2;

    public JFXListView nameList;
    public JFXChipView a;


    public JFXButton goBackButton;
    public JFXButton choose;
    public JFXButton showNameMangerButton;
    public  JFXButton recover;

    public JFXCheckBox taoluModeBtn;
    public JFXCheckBox equalModeBtn;

    public JFXRadioButton numbChoose;
    public JFXRadioButton nameChoose;

    public JFXRadioButton chooseOnce;
    public JFXRadioButton ignoreOnce;

    public JFXRadioButton randomTimes;
    public JFXRadioButton fixedTimes;

    public JFXSlider chooseTimes;
    public JFXSlider speedBar;
    public JFXTextArea inputName;

    public Pane numbPane;
    public Pane namePane;
    public Pane controllerPane;

    public Stage stage;
    public Scene scene;

    public Pane mainPane;

    short oldX;
    short oldY;
    short oldW;
    short oldH;

    private Data data=new Data();

    public boolean isRandomTimes=true;

    public Config config;

    public static final ObservableList names = FXCollections.observableArrayList();

    public void setStage(Stage stage){
        this.stage = stage;
    }
    public void setScene(Scene scene){
        this.scene = scene;
    }


    public void setConfig(Config config) {
        this.config = config;
    }


    public void setMinNumber(short minNumber) {
        this.minNumber = minNumber;
    }

    public void setMaxNumber(short maxNumber) {
        this.maxNumber = maxNumber;
    }

    public void setChosenTime(int chosenTime) {
        this.chosenTime = chosenTime;
        chooseTimes.setValue(chosenTime);
    }

    public void setSpeed(short speed) {
        this.speed = speed;
        speedBar.setValue(100-speed);
    }


    public void setChosenTimeHere() {
        this.chosenTime = (int)chooseTimes.getValue();
    }

    public void setSpeedHere() {
        this.speed = (short) speedBar.getValue();
    }

    public void setTaoluMode(boolean taoluMode){
        this.taoluMode=taoluMode;
    }


    final static private String CONFIG_FILE="D:\\DM_Master_sources-master\\config";
    final private File configFile=new File(CONFIG_FILE);

    public int saveConfigToFile(){

        config.setChosenTime(chosenTime);
        config.setIgnorePast(ignorePast);
        config.setMaxNumber(maxNumber);
        config.setMinNumber(minNumber);
        config.setNameChoose(isNameChoose);
        config.setSpeed(speed);
        config.setRandomTimes(isRandomTimes);
        config.setTaoluMode(taoluMode);
        config.setEqualMode(equalMode);

        System.out.println(speed);
        System.out.println(chosenTime);

        try{
            ObjectOutputStream oos =new ObjectOutputStream(new FileOutputStream(configFile));
            oos.writeObject(config);
            oos.close();
            return 0;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }



    }

    
    boolean isRunning=false;

    @FXML
    void anPai(){

        if(isRunning){
            forceStop=true;
            choose.setText("安排一下");
            return;
        }

        if(isRandomTimes) {
            chosenTime =  100 + (int) (Math.random() * (250 - 100));
            chooseTimes.setValue(chosenTime);
        }
        else
            chosenTime=(int)chooseTimes.getValue();


        //int s=(int)min+(int)(Math.random()*(max-min));

        if(isNameChoose){
            if(data.isEmpty(taoluMode)){
                showInfoDialog("哦霍~","现在名单还是空的捏~请前往名单管理添加名字 或 使用数字挑选法。");
                return;
             }

             if(ignoreNameList.size()>=data.getSize()&&ignorePast){                    if(equalMode) {
                 showInfoDialog("啊？", "全部名字都被点完啦！\n名字列表将会重置");
                 clearIgnoreList();
             }else
                 showInfoDialog("啊？","全部名字都被点完啦！\n请多添加几个名字 或 点击“机会均等”的“重置”按钮。");
                 return;
             }
            controllerPane.setDisable(true);
            speed=(short) (100-speedBar.getValue());
            isRunning=true;
            choose.setText("不玩了！");
            timer.start();

        }else {

            try{
                minNumber=Short.parseShort(minNumb.getText());
                maxNumber=Short.parseShort(maxNumb.getText());

                if(maxNumber-minNumber<0){
                    showInfoDialog("嗯哼？","数字要前小后大啊~");
                    return;
                }

                if(ignoreNumberList.size()>=(maxNumber-minNumber) && ignorePast){
                    if(equalMode) {
                        showInfoDialog("啊？", "全部数字都被点完啦！\n数字列表将会重置");
                        clearIgnoreList();
                    }else
                        showInfoDialog("啊？","全部数字都被点完啦！\n请扩大数字范围 或 点击“机会均等”的“重置”按钮。");
                    return;
                }

            }catch (Exception e){
                showInfoDialog("嗯哼？","倒是输入个有效的数字啊~");
                return;
            }

            controllerPane.setDisable(true);
            speed=(short) (100-speedBar.getValue());
            isRunning=true;
            choose.setText("不玩了！");
            numbTimer.start();

        }

    }



    //两种选择方式的切换，没什么好说的。
    @FXML
    void numbChoose_selected(){
        isNameChoose=false;
        numbChoose.setSelected(true);
        namePane.setVisible(false);
        nameChoose.setSelected(false);
        numbPane.setVisible(true);
        showNameMangerButton.setVisible(false);

        taoluMode=false;
        taoluModeBtn.setSelected(false);
        taoluModeBtn.setDisable(false);

    }

    @FXML
    void nameChoose_selected(){
        isNameChoose=true;
        nameChoose.setSelected(true);
        numbPane.setVisible(false);
        numbChoose.setSelected(false);
        namePane.setVisible(true);
        showNameMangerButton.setVisible(true);
        if(!ignorePast){
            //taoluMode=true;
            taoluModeBtn.setDisable(true);
        }
    }
    
    
    void taoluModeBtn_selected(){
        taoluMode=true;
        taoluModeBtn.setSelected(true);
    }

    void taoluModeBtn_unselect(){
        taoluMode=false;
        taoluModeBtn.setSelected(false);
    }

   @FXML
   void taoluModeBtn_Aciton(){
        if(taoluMode)
            taoluModeBtn_unselect();
        else
            taoluModeBtn_selected();
   }

   @FXML
   void equalBtnAction(){
       if(equalMode)
           unSelectEqualBtn();
       else
           selectEqualBtn();
   }

   @FXML
   void selectEqualBtn(){
        equalModeBtn.setSelected(true);
        equalMode=true;
   }

   @FXML
   void unSelectEqualBtn(){
       equalModeBtn.setSelected(false);
       equalMode=false;
   }

    @FXML
    void addName(){
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
        final KeyValue kv = new KeyValue(namePane.layoutXProperty(), 0,Interpolator.EASE_BOTH);
        final KeyFrame kf = new KeyFrame(Duration.millis(400), kv);


        final KeyValue kv2 = new KeyValue(mainPane.layoutXProperty(), -mainPane.getWidth()/2,Interpolator.EASE_BOTH);
        final KeyFrame kf2 = new KeyFrame(Duration.millis(400), kv2);

        timeline.getKeyFrames().add(kf);
        timeline.getKeyFrames().add(kf2);

        timeline.play();

    }
    
    @FXML
    void turnBack() {
        
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(true);
        final KeyValue kv = new KeyValue(namePane.layoutXProperty(), namePane.getWidth(),Interpolator.EASE_BOTH);
        final KeyFrame kf = new KeyFrame(Duration.millis(400), kv);


        final KeyValue kv2 = new KeyValue(mainPane.layoutXProperty(), 0,Interpolator.EASE_BOTH);
        final KeyFrame kf2 = new KeyFrame(Duration.millis(400), kv2);

        timeline.getKeyFrames().add(kf);
        timeline.getKeyFrames().add(kf2);

        timeline.play();
    }
    
    
    @FXML
    void goBack() {

        Scene scene=mainPane.getScene();
        stage=(Stage)scene.getWindow();

        oldX=(short) stage.getX();
        oldY=(short) stage.getY();
        oldW=(short)stage.getWidth();
        oldH=(short)stage.getHeight();

        EventHandler eventHandler=new MoveWindow(stage);

        scene.setOnMousePressed(eventHandler);
        scene.setOnMouseDragged(eventHandler);

        EventHandler hander=new MoveWindowByTouch(stage);

        scene.setOnTouchPressed(hander);
        scene.setOnTouchMoved(hander);

        goBackButton.setOnMousePressed(eventHandler);
        goBackButton.setOnMouseDragged(eventHandler);

        goBackButton.setOnTouchPressed(hander);
        goBackButton.setOnTouchMoved(hander);

        stage.setAlwaysOnTop(true);
/*
        stage.close();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
*/
        stage.setWidth(30);
        stage.setHeight(100);
        stage.setY(stage.getY()+stage.getHeight()-50);
        mainPane.setVisible(false);
        recover.setVisible(true);

    }

    @FXML
    void recoverWindow(){
        Scene scene=mainPane.getScene();
        stage=(Stage)scene.getWindow();

        scene.setOnMousePressed(null);
        scene.setOnMouseDragged(null);

        scene.setOnTouchPressed(null);
        scene.setOnTouchMoved(null);

        goBackButton.setOnMousePressed(null);
        goBackButton.setOnMouseDragged(null);

        goBackButton.setOnTouchPressed(null);
        goBackButton.setOnTouchMoved(null);

        stage.setAlwaysOnTop(false);
/*
        stage.close();
        stage.initStyle(StageStyle.DECORATED);
        stage.show();
*/
        stage.setResizable(false);

        stage.setWidth(oldW);
        stage.setHeight(oldH);
        stage.setY(oldY);
        mainPane.setVisible(true);
        recover.setVisible(false);
    }



    @FXML
    void deleteAllName(){
        data.deleteAll();
        clearIgnoreList();
        clearTaoluList();
        names.clear();
        data.saveToFile();
    }

    @FXML
    void ignoreOnce_selected() {
        ignorePast=true;
        ignoreOnce.setSelected(true);
        chooseOnce.setSelected(false);

        taoluMode=false;
        taoluModeBtn.setDisable(true);
        taoluModeBtn.setSelected(false);

        equalModeBtn.setDisable(false);

    }
    
    @FXML
    void chooseOnce_selected() {
        ignorePast=false;
        chooseOnce.setSelected(true);
        ignoreOnce.setSelected(false);
        unSelectEqualBtn();
        equalModeBtn.setDisable(true);
        if(isNameChoose){
            //taoluMode=true;
            taoluModeBtn.setDisable(false);
            
        }
    }


    @FXML
    void randomTimes_selected() {
        isRandomTimes=true;
        fixedTimes.setSelected(false);
        randomTimes.setSelected(true);
    }


    @FXML
    void fixedTimes_selected() {
        isRandomTimes=false;
        fixedTimes.setSelected(true);
        randomTimes.setSelected(false);
    }

    public void showInfoDialog(String header,String message) {
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text(header));
        content.setBody(new Text(message));
        StackPane tempPane=new StackPane();
        tempPane.setPrefHeight(mainPane.getPrefHeight());
        tempPane.setPrefWidth(mainPane.getPrefWidth());
        mainPane.getChildren().add(tempPane);
        JFXDialog dialog = new JFXDialog(tempPane,content,JFXDialog.DialogTransition.TOP);
        dialog.setPrefHeight(mainPane.getPrefHeight());
        dialog.setPrefWidth(mainPane.getPrefWidth());
        JFXButton button = new JFXButton("OK");
        button.setPrefWidth(50);
        button.setPrefHeight(30);
        button.setOnAction((ActionEvent e) -> {
            dialog.close();
            mainPane.getChildren().remove(tempPane);
        });
        content.setActions(button);
        dialog.show();
    }


}
