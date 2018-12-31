package main;

import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;



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

    public Pane numbPane;
    public Pane namePane;

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
    void a(){
        int aa=0;
        for(int i=0;i<200;i++) {
            if(aa==0){
                chosen_1.setText("aaaaa");
                System.out.println("asas");
                aa=1;
                }else
             if(aa==1){
                chosen_1.setText("bbbbb");

                 System.out.println("vvv");
                aa=0;
             }
            }
        }
}
