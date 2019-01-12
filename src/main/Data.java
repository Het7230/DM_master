package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Data {
    private List<String> namelist = new ArrayList<String>();
    private int listSize = 0;

    //------------------------------------------------------
    public void add(String text){
        String[] temp;

        if(text.contains("。")){
            temp=text.split("。");
            for(int i=0;i<temp.length;i++)
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

        for (String myString :namelist.toArray(new String[0])) {
            System.out.println(myString);
        }
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

    //------------------------------------------------------
    public String[] getAll(){
        for (String myString :namelist.toArray(new String[0])) {
            System.out.println(myString);
        }
        return namelist.toArray(new String[0]);
    }

}



