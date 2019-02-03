package main;

import main.sourcesData.*;
import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.FileOutputStream;

public class releaseData {

    final static private String UI_FILE="D:\\DM_Master_sources-master\\sources\\UI.fxml";
    final static private String IAMGE_FILE="D:\\DM_Master_sources-master\\sources\\img1.png";
    final static private String BACKIAMGE_FILE="D:\\DM_Master_sources-master\\sources\\back.png";
    final static private String MAIN_DIR="D:\\DM_Master_sources-master\\sources\\";


    private final File mainDir=new File(MAIN_DIR);
    final static private File imageFile=new File(IAMGE_FILE);
    final static private File backImageFile=new File(BACKIAMGE_FILE);
    final static private File fxmlFile=new File(UI_FILE);

    private Base64 base64 =new Base64();

    public int releaseAllFile(){
        try{
            if(!mainDir.exists())
                mainDir.mkdirs();

            FileOutputStream UIfos =new FileOutputStream(fxmlFile);
            FileOutputStream imageFos =new FileOutputStream(imageFile);
            FileOutputStream backFos =new FileOutputStream(backImageFile);

            UIfos.write(base64.decode(UIFileData.data));
            UIfos.close();

            backFos.write(base64.decode(backData.data));
            backFos.close();

            StringBuffer sb =new StringBuffer();
            sb.append(img1Data_1.data);
            sb.append(img1Data_2.data);

            imageFos.write(base64.decode(sb.toString()));
            imageFos.close();

            return 0;

        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

}
