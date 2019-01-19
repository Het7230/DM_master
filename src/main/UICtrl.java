package main;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class UICtrl {

    public Boolean isNameChoose=true;
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
    public JFXTextArea inputName;

    public Pane numbPane;
    public Pane namePane;

    public Stage stage;
    public Scene scene;

    private Data data=new Data();

    public static final ObservableList names = FXCollections.observableArrayList();

    int chosenTime;


    public void setStage(Stage stage){
        this.stage = stage;
    }
    public void setScene(Scene scene){
        this.scene = scene;
    }
    public void setdata(Data data){
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
    }
}
