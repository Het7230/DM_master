package me.hety.dogename.main.voice;

import javazoom.spi.mpeg.sampled.file.MpegAudioFileReader;
import okhttp3.*;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;

import javax.sound.sampled.*;
import java.io.*;
import java.net.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class VoicePlayer {

    public static final String separator=File.separator;

    Logger log=Logger.getLogger("VoicePlayerLogger");

    private final String VOICE_API="https://tsn.baidu.com/text2audio";

    Token token;

    public VoicePlayer(Token token){
        this.token=token;
    }

    String cachedVoicePath="caches"+separator+"voice"+separator;

    File cacheDir =new File(cachedVoicePath);

    OkHttpClient okHttpClient=new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10,TimeUnit.SECONDS)
            .readTimeout(10,TimeUnit.SECONDS)
            .build();


    public void playVoice(String name,String speaker,String intonation,String speed) {

        String cachedVoiceName;
        cachedVoiceName = name + "_" + speaker+ "_" + speed +"_"+intonation;

        File cachedVoice = new File(cachedVoicePath + cachedVoiceName + ".mp3");

        if (!cachedVoice.exists()) {

            getVoiceData(name,speaker,speed,intonation,cachedVoice);
            playSound(cachedVoice);

        } else {
            new Thread(() -> {
                playSound(cachedVoice);
            });
        }

    }

    private void getVoiceData(String name,String speaker,String speed,String intonation,File cachedVoice){

        new Thread(() -> {
            try{

                FormBody formBody=new FormBody.Builder()
                        .add("tex",URLEncoder.encode(name,"utf-8"))
                        .add("tok",token.getAccessToken())
                        .add("cuid",getMACAddress())
                        .add("ctp","1")
                        .add("lan","zh")
                        .add("spd",speed)
                        .add("per",speaker)
                        .add("pit",intonation)
                        .add("aue","6")
                        .build();

                Request request=new Request.Builder()
                        .url(VOICE_API)
                        .header("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36")
                        .post(formBody)
                        .build();


                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        log.warning("Failed to get voice:"+e.toString());
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) {
                        if(request.header("Content-type").contains("json")){
                            log.warning("Request error:"+response.toString());
                        }else {
                            boolean success=cacheVoiceFile(response,cachedVoice);
                            if (success) {
                                playSound(cachedVoice);
                            }
                        }
                    }
                });

            }catch(Exception e){
                e.printStackTrace();
            }

        }).start();
    }

    private boolean cacheVoiceFile(Response response,File cacheVoice){

        try {

            if(!cacheDir.exists())
            cacheDir.mkdirs();

            if(!cacheVoice.exists())
                cacheVoice.createNewFile();

            FileOutputStream cacheFile=new FileOutputStream(cacheVoice);
            IOUtils.write(response.body().bytes(),cacheFile);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            log.warning("Error to cache voice file:"+e.toString());
            return false;
        }

    }


    private void playSound(File file) {

        try {
            //使用 mp3spi 解码 mp3 音频文件
            MpegAudioFileReader mp = new MpegAudioFileReader();
            AudioInputStream stream = mp.getAudioInputStream(file);
            AudioFormat baseFormat = stream.getFormat();
            //设定输出格式为pcm格式的音频文件
            AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16, baseFormat.getChannels(), baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);
            // 输出到音频
            stream = AudioSystem.getAudioInputStream(format, stream);
            AudioFormat target = stream.getFormat();
            DataLine.Info dinfo = new DataLine.Info(SourceDataLine.class, target, AudioSystem.NOT_SPECIFIED);
            SourceDataLine line = null;
            int len = -1;
            line = (SourceDataLine) AudioSystem.getLine(dinfo);
            line.open(target);
            line.start();
            byte[] buffer = new byte[1024];
            while ((len = stream.read(buffer)) > 0) {
                line.write(buffer, 0, len);
            }
            line.drain();
            line.stop();
            line.close();
        } catch (Exception e) {
            log.warning("Error to play voice audio:"+e.toString());
        }
    }


    private static String getMACAddress() {

        try{

            byte[] mac = NetworkInterface.getByInetAddress(InetAddress.getLocalHost()).getHardwareAddress();

            StringBuffer sb = new StringBuffer();

            for (int i = 0; i < mac.length; i++) {
                if (i != 0) {
                    sb.append("-");
                }
                String s = Integer.toHexString(mac[i] & 0xFF);
                sb.append(s.length() == 1 ? 0 + s : s);
            }
            return sb.toString().toUpperCase();

        }catch (Exception e){
            return String.valueOf(new Random().nextLong());
        }

    }

}