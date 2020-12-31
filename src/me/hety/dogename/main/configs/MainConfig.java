package me.hety.dogename.main.configs;

import com.google.gson.annotations.Expose;
import javafx.beans.property.*;

public class MainConfig {

    // ConfigValuesBean configValuesBean =new ConfigValuesBean();

    // ---------------------- Default values ---------------------------------------------------------

    @Expose
    private final String NEWEST_CONFIG_VERSION = "2";

    private String currentConfigVersion = null;

    @Expose
    public final boolean DEFAULT_NAME_CHOOSE = true;

    public final int METHOD_NAME = 0; // 名字挑选法
    public final int METHOD_NUMBER = 1; // 数字挑选法

    public final int DEFAULT_CYCLE_TIMES = 120; // 默认轮回次数:120
    public final int DEFAULT_SPEED = 80; // 默认速度:20ms,对应滑动条80的位置

    public final boolean DEFAULT_RANDOM_TIMES = true; // 默认挑选轮回次数是否随机:ture

    public final boolean DEFAULT_IGNORE_PAST = true; // 默认忽略已经点过的名字:ture

    public final boolean DEFAULT_EQUAL_MODE = true; // 默认开启"机会均等"

    public final boolean DEFAULT_NEW_ALGO = true; // 默认使用新算法"Java sec random"
    public final boolean DEFAULT_VOICE_PLAY = true; // 默认使用语音播报

    public final boolean DEFAULT_SHOW_SAYING = true;

    // ----------------------Properties----------------------------------------------------------------

    private SimpleBooleanProperty nameChooseProperty;

    private SimpleBooleanProperty randomTimesProperty; // 挑选次数是否随机
    private SimpleBooleanProperty ignorePastProperty; // 是否忽略已经被点过的名字

    private SimpleIntegerProperty chooseMethodProperty; // 挑选方式: 0->名字挑选法 1->数字挑选法
    private SimpleIntegerProperty cycleTimesProperty; // 挑选轮回次数,旧称"chosenTime"

    private SimpleIntegerProperty speedProperty; // 速度

    private SimpleStringProperty minNumberProperty; // 最小值
    private SimpleStringProperty maxNumberProperty; // 最大值

    private SimpleBooleanProperty equalModeProperty; // 是否开启"机会均等"

    private SimpleBooleanProperty newAlgoProperty; // 是否使用新算法
    private SimpleBooleanProperty voicePlayProperty; // 是否使用语音播报

    private SimpleBooleanProperty showSaying;

    // -------------------------- 初始化 --------------------------------------------------------------
    public MainConfig() {
        randomTimesProperty = new SimpleBooleanProperty(DEFAULT_RANDOM_TIMES);
        ignorePastProperty = new SimpleBooleanProperty(DEFAULT_IGNORE_PAST);

        chooseMethodProperty = new SimpleIntegerProperty(METHOD_NAME);
        nameChooseProperty = new SimpleBooleanProperty(DEFAULT_NAME_CHOOSE);

        cycleTimesProperty = new SimpleIntegerProperty(DEFAULT_CYCLE_TIMES);

        speedProperty = new SimpleIntegerProperty(DEFAULT_SPEED);

        minNumberProperty = new SimpleStringProperty("0");
        maxNumberProperty = new SimpleStringProperty("10");

        equalModeProperty = new SimpleBooleanProperty(DEFAULT_EQUAL_MODE);

        newAlgoProperty = new SimpleBooleanProperty(DEFAULT_NEW_ALGO);
        voicePlayProperty = new SimpleBooleanProperty(DEFAULT_VOICE_PLAY);

        showSaying = new SimpleBooleanProperty(DEFAULT_SHOW_SAYING);

        currentConfigVersion = NEWEST_CONFIG_VERSION;
    }

    // -------------------------- Getters and Setters ---------------------------------------------

    public boolean isNameChooseProperty() {
        return nameChooseProperty.get();
    }

    public SimpleBooleanProperty nameChoosePropertyProperty() {
        return nameChooseProperty;
    }

    public void setNameChooseProperty(boolean nameChooseProperty) {
        this.nameChooseProperty.set(nameChooseProperty);
    }

    public boolean isRandomTimesProperty() {
        return randomTimesProperty.get();
    }

    public SimpleBooleanProperty randomTimesPropertyProperty() {
        return randomTimesProperty;
    }

    public void setRandomTimesProperty(boolean randomTimesProperty) {
        this.randomTimesProperty.set(randomTimesProperty);
    }

    public boolean isIgnorePastProperty() {
        return ignorePastProperty.get();
    }

    public SimpleBooleanProperty ignorePastPropertyProperty() {
        return ignorePastProperty;
    }

    public void setIgnorePastProperty(boolean ignorePastProperty) {
        this.ignorePastProperty.set(ignorePastProperty);
    }

    public int getChooseMethodProperty() {
        return chooseMethodProperty.get();
    }

    public SimpleIntegerProperty chooseMethodPropertyProperty() {
        return chooseMethodProperty;
    }

    public void setChooseMethodProperty(int chooseMethodProperty) {
        this.chooseMethodProperty.set(chooseMethodProperty);
    }

    public int getCycleTimesProperty() {
        return cycleTimesProperty.get();
    }

    public SimpleIntegerProperty cycleTimesPropertyProperty() {
        return cycleTimesProperty;
    }

    public void setCycleTimesProperty(int cycleTimesProperty) {
        this.cycleTimesProperty.set(cycleTimesProperty);
    }

    public int getSpeedProperty() {
        return speedProperty.get();
    }

    public SimpleIntegerProperty speedPropertyProperty() {
        return speedProperty;
    }

    public void setSpeedProperty(int speedProperty) {
        this.speedProperty.set(speedProperty);
    }

    public String getMinNumberProperty() {
        return minNumberProperty.get();
    }

    public SimpleStringProperty minNumberPropertyProperty() {
        return minNumberProperty;
    }

    public void setMinNumberProperty(String minNumberProperty) {
        this.minNumberProperty.set(minNumberProperty);
    }

    public String getMaxNumberProperty() {
        return maxNumberProperty.get();
    }

    public SimpleStringProperty maxNumberPropertyProperty() {
        return maxNumberProperty;
    }

    public void setMaxNumberProperty(String maxNumberProperty) {
        this.maxNumberProperty.set(maxNumberProperty);
    }

    public boolean isEqualModeProperty() {
        return equalModeProperty.get();
    }

    public SimpleBooleanProperty equalModePropertyProperty() {
        return equalModeProperty;
    }

    public void setEqualModeProperty(boolean equalModeProperty) {
        this.equalModeProperty.set(equalModeProperty);
    }

    public boolean isNewAlgoProperty() {
        return newAlgoProperty.get();
    }

    public SimpleBooleanProperty newAlgoPropertyProperty() {
        return newAlgoProperty;
    }

    public void setNewAlgoProperty(boolean newAlgoProperty) {
        this.newAlgoProperty.set(newAlgoProperty);
    }

    public boolean isVoicePlayProperty() {
        return voicePlayProperty.get();
    }

    public SimpleBooleanProperty voicePlayPropertyProperty() {
        return voicePlayProperty;
    }

    public void setVoicePlayProperty(boolean voicePlayProperty) {
        this.voicePlayProperty.set(voicePlayProperty);
    }

    public boolean isShowSaying() {
        return showSaying.get();
    }

    public SimpleBooleanProperty showSayingProperty() {
        return showSaying;
    }

    public void setShowSaying(boolean showSaying) {
        this.showSaying.set(showSaying);
    }

    public String getCurrentConfigVersion() {
        return currentConfigVersion;
    }

    public void setCurrentConfigVersion(String currentConfigVersion) {
        this.currentConfigVersion = currentConfigVersion;
    }

    public String getNewestConfigVersion() {
        return NEWEST_CONFIG_VERSION;
    }

}
