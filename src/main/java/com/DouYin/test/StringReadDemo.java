package com.DouYin.test;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author wyz
 * @create 2021-06-14 22:41
 */
public class StringReadDemo {
    public static void main(String[] args) {
        String filePath = "D:\\a.txt";
        HashMap<?, ?> getMap=Read(filePath);

        for(Object key:getMap.keySet()){
            System.out.println("key:"+key+" "+"Value:"+getMap.get(key));
        }
    }
    public static HashMap Read(String path){
        HashMap<?,?> map = readFileByLine(path);
        return map;
    }


    /**
     * 按行读取文件
     * @param strFile
     */
    public static HashMap<Integer, String> readFileByLine(String strFile){
        int lineCount = 0;
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        try {
            File file = new File(strFile);
            InputStream is = new FileInputStream(file);
            InputStreamReader inputReader = new InputStreamReader(is);
            BufferedReader bf = new BufferedReader(inputReader);
            String strLine;
            while((strLine = bf.readLine()) != null){
                String str2=strLine;
                if(strLine.length()>20){
//                    System.out.println(str2);
                    String s = deal(str2);
                map.put(lineCount, s);
                lineCount++;
                }
            }
            bf.close();
            inputReader.close();
            is.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return map;
    }

    public static String deal(String Str){
        int x;
        int y;
        String b = null;
        x = Str.indexOf("https");
        String[] list = {"复","緮","覆","複","復"};
        for (int i = 0; i < list.length; i++) {
            if(Str.contains(list[i])){
                y = Str.indexOf(list[i]);
                b = Str.substring(x,y).trim();
            }
        }
        return b;
    }
}
