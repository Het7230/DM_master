package main;

import java.util.List;

import com.google.gson.Gson;

public class Update {

    final String UPDATE_URL="https://gitee.com/hety2002/dogename/raw/master/updateFiles/update.json";
    UpdateInfo updateInfo;
    
    public boolean checkUpdate() {
	updateInfo =new Gson().fromJson(Common.getHtml(UPDATE_URL,false), UpdateInfo.class);
	
	if(updateInfo.getVer()>nowVer)
	    return true;
	else
	    return false;
    }
    
    public String[] getUpdateURL() {
	return updateInfo.getResources().get(0).getUpdateURL().toArray(new String[0]);
    }

    public  String getFirstFileName(){
        String[] temp =updateInfo.getResources().get(0).getUpdateURL().toArray(new String[0]);
        String[] temp2=temp[0].split("/");
        return temp2[temp2.length-1];
    }

    class UpdateInfo {

       private int ver;
       private String desc;
       private List<UpdateResources> resources;
       public void setVer(int ver) {
            this.ver = ver;
        }
        public int getVer() {
            return ver;
        }

       public void setDesc(String desc) {
            this.desc = desc;
        }
        public String getDesc() {
            return desc;
        }

       public void setResources(List<UpdateResources> resources) {
            this.resources = resources;
        }
        public List<UpdateResources> getResources() {
            return resources;
        }

        
        public class UpdateResources {

            private String type;
            private List<String> updateURL;
            public void setType(String type) {
                 this.type = type;
             }
             public String getType() {
                 return type;
             }

            public void setUpdateURL(List<String> updateURL) {
                 this.updateURL = updateURL;
             }
             public List<String> getUpdateURL() {
                 return updateURL;
             }

        }
        
   }
}
