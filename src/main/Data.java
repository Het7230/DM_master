package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Data {
    private List<String> namelist = new ArrayList();
    private int listSize = 0;

    //------------------------------------------------------
    public void add(String text){
        String[] temp;

        if(text.contains("。")){
            temp=text.split("。");
            for(int i=0;i<temp.length-1;i++)
                namelist.add(temp[i]);
        }else {
            namelist.add(text);
            listSize=namelist.size();
        }
    }
    //------------------------------------------------------
    public String get(int i){
        if(i<listSize-1)
            return null;
        else
            return namelist.get(i);
    }

    //------------------------------------------------------
    public void delete(String name){
        if(namelist.isEmpty())
            return;
        namelist.remove(name);
        listSize=namelist.size();
    }

    //------------------------------------------------------
    public boolean isEmpty(){
        return  namelist.isEmpty();
    }

    //------------------------------------------------------
    public String ranDomGet(){
        int i=(int)(0+Math.random()*(namelist.size()));
        return  namelist.get(i);
    }

}



