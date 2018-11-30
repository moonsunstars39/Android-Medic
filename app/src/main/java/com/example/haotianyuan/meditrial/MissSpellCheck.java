package com.example.haotianyuan.meditrial;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;


public class MissSpellCheck {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        HashMap<String, String> spellMatch = new HashMap<String,String>();
        List<String> list = new ArrayList<>();
        MatchMissSpell(spellMatch);
        String keyword = "cacer";
        System.out.println(spellMatch);
        if(spellMatch.containsKey(keyword)){
            System.out.println(keyword);
            keyword = spellMatch.get(keyword);
        }

        list = MatchContain(keyword); //Input keyword, search in "contain.txt" to find matching
        for(int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
    public static void MatchMissSpell(HashMap<String,String> map)
    {
        try
        {
            String encoding = "GBK";

            File file = new File(System.getProperty("user.dir") + "/miss.txt");

            if (file.isFile() && file.exists())
            {
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;

                while ((lineTxt = bufferedReader.readLine()) != null)
                {
                    //System.out.println(lineTxt);
                    String[] temp = lineTxt.split("->");
                    String miss = temp[0];
                    String correct = temp[1];
                    map.put(miss, correct);
                }
                bufferedReader.close();
                read.close();
            }
            else
            {
                System.err.println("Error: cannot find file");
            }
        }
        catch (Exception e)
        {
            System.err.println("ERROR: cannot read file");
            e.printStackTrace();
        }
    }
    public static List<String> MatchContain(String keyWord)
    {
        List<String> list = new ArrayList<String>();
        try
        {
            String encoding = "GBK";
            System.err.println("here:" +System.getProperty("user.dir"));
            File file = new File(System.getProperty("user.dir") + "/contain.txt");
            if (file.isFile() && file.exists())
            {
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                String[] temp = keyWord.split(" ");
                while ((lineTxt = bufferedReader.readLine()) != null)
                {
                    String company = lineTxt;
                    for(int i = 0; i < temp.length;i++) {
                        if(company.contains(temp[i])) {
                            list.add(company);
                        }
                    }
                }
                bufferedReader.close();
                read.close();
            }
            else
            {
                System.err.println("Error: cannot find file");
            }
        }
        catch (Exception e)
        {
            System.err.println("ERROR: cannot read file");
            e.printStackTrace();
        }
        return list;
    }
}
