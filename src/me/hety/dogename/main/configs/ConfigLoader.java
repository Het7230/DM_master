package me.hety.dogename.main.configs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.beans.property.*;
import me.hety.dogename.main.configs.adapters.BooleanPropertyAdapter;
import me.hety.dogename.main.configs.adapters.DoublePropertyAdapter;
import me.hety.dogename.main.configs.adapters.IntegerPropertyAdapter;
import me.hety.dogename.main.configs.adapters.StringPropertyAdapter;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ConfigLoader {

    Logger log = LogManager.getLogger();

    //ConfigValuesBean config;
    private MainConfig mainConfig;
    private VoiceConfig voiceConfig;
    
    private final String mainConfigLocation = "files"+ File.separator+"Config.json";
    private final String voiceConfigLocation = "files"+ File.separator+"VoiceConfig.json";

    public String getMainConfigLocation() {
        return mainConfigLocation;
    }

    public String getVoiceConfigLocation() {
        return voiceConfigLocation;
    }

    public MainConfig getMainConfig() {
        return mainConfig;
    }

    public MainConfig readConfigFromFile(String fileLocation){

        //property属性应该要自定义一个json适配器才能解析出来
        Gson gson=new GsonBuilder()
                .registerTypeAdapter(SimpleBooleanProperty.class,new BooleanPropertyAdapter())
                .registerTypeAdapter(SimpleIntegerProperty.class,new IntegerPropertyAdapter())
                .registerTypeAdapter(SimpleStringProperty.class,new StringPropertyAdapter())
                .registerTypeAdapter(SimpleDoubleProperty.class,new DoublePropertyAdapter())
                .setPrettyPrinting()
                .create();

        String ConfigJSON;

        try{
            File configFile=new File(fileLocation);
            if(!configFile.exists()){
                configFile.getParentFile().mkdirs();
                configFile.createNewFile();
                mainConfig=new MainConfig();
                writeMainConfigToFile(mainConfigLocation);
                return mainConfig;
            }
            InputStream inputStream=new FileInputStream(configFile);
            ConfigJSON=IOUtils.toString(inputStream, StandardCharsets.UTF_8);

            mainConfig=gson.fromJson(ConfigJSON,MainConfig.class);

            if (mainConfig == null) {
                mainConfig=new MainConfig();
                writeMainConfigToFile(mainConfigLocation);
                return mainConfig;
            }

        }catch (Exception e){
            log.error("Error to load config file:"+e+"\nUse Default config.");

            mainConfig=new MainConfig();
            writeMainConfigToFile(mainConfigLocation);
            return mainConfig;
        }

        return this.mainConfig;
    }

    public VoiceConfig readVoiceConfigFromFile(String fileLocation){

        //property属性应该要自定义一个json适配器才能解析出来
        Gson gson=new GsonBuilder()
                .registerTypeAdapter(SimpleBooleanProperty.class,new BooleanPropertyAdapter())
                .registerTypeAdapter(SimpleIntegerProperty.class,new IntegerPropertyAdapter())
                .registerTypeAdapter(SimpleStringProperty.class,new StringPropertyAdapter())
                .registerTypeAdapter(SimpleDoubleProperty.class,new DoublePropertyAdapter())
                .setPrettyPrinting()
                .create();

        String ConfigJSON;

        try{
            File configFile=new File(fileLocation);
            if(!configFile.exists()){
                configFile.getParentFile().mkdirs();
                configFile.createNewFile();

                voiceConfig=new VoiceConfig();
                writeVoiceConfigToFile(voiceConfigLocation);
                return voiceConfig;
            }
            InputStream inputStream=new FileInputStream(configFile);
            ConfigJSON=IOUtils.toString(inputStream, StandardCharsets.UTF_8);

            writeVoiceConfigToFile(voiceConfigLocation);
            voiceConfig=gson.fromJson(ConfigJSON,VoiceConfig.class);
            if (voiceConfig == null) {
                voiceConfig=new VoiceConfig();
                writeVoiceConfigToFile(voiceConfigLocation);
                return voiceConfig;
            }

        }catch (Exception e){
            log.error("Error to load voice config file:"+e+"\nUse Default voice config.");

            voiceConfig=new VoiceConfig();
            writeVoiceConfigToFile(voiceConfigLocation);
            return voiceConfig;
        }

        return this.voiceConfig;
    }

    //
    public MainConfig setValuesToProperty(){
        //mainconfig.set..(config.get..)
        //...so on
        //
        return this.mainConfig;
    }

    private String toJSON(MainConfig config){

        Gson gson=new GsonBuilder()
            .registerTypeAdapter(SimpleBooleanProperty.class,new BooleanPropertyAdapter())
            .registerTypeAdapter(SimpleIntegerProperty.class,new IntegerPropertyAdapter())
            .registerTypeAdapter(SimpleStringProperty.class,new StringPropertyAdapter())
            .setPrettyPrinting()
            .create();

        return gson.toJson(config);
    }

    private String VoiceConfigtoJSON(VoiceConfig config){

        Gson gson=new GsonBuilder()
                .registerTypeAdapter(SimpleDoubleProperty.class,new DoublePropertyAdapter())
                .setPrettyPrinting()
                .create();

        return gson.toJson(config);
    }

    public void writeAllConfigToFile(String outputLocation, String voiceConfigFile){
        File outputFile = new File(outputLocation);

        try{
            if(! outputFile.exists()){
                outputFile.getParentFile().mkdirs();
                outputFile.createNewFile();
            }
            OutputStream stream=new FileOutputStream(outputFile);
            IOUtils.write(toJSON(this.mainConfig).getBytes(StandardCharsets.UTF_8),stream);

            OutputStream voiceConfigFileStream=new FileOutputStream(voiceConfigFile);
            IOUtils.write(VoiceConfigtoJSON(this.voiceConfig).getBytes(StandardCharsets.UTF_8),voiceConfigFileStream);

        }catch (Exception e){
            log.error("Error in writing all config:"+e);
        }
    }

    public void writeMainConfigToFile(String outputLocation){
        File outputFile = new File(outputLocation);

        try{
            if(! outputFile.exists()){
                outputFile.getParentFile().mkdirs();
                outputFile.createNewFile();
            }

            OutputStream stream=new FileOutputStream(outputFile);
            IOUtils.write(toJSON(this.mainConfig).getBytes(StandardCharsets.UTF_8),stream);

        }catch (Exception e){
            log.error("Error in writing main config:"+e);
        }
    }

    public void writeVoiceConfigToFile(String voiceConfigFile){
        File outputFile = new File(voiceConfigFile);

        try{

            if(! outputFile.exists()) {
                outputFile.getParentFile().mkdirs();
                outputFile.createNewFile();
            }
            OutputStream voiceConfigFileStream=new FileOutputStream(voiceConfigFile);
            IOUtils.write(VoiceConfigtoJSON(this.voiceConfig).getBytes(StandardCharsets.UTF_8),voiceConfigFileStream);

        }catch (Exception e){
            log.error("Error in writing voice config:"+e);
        }
    }

}
