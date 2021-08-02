package com.DouYin.test;

import java.util.HashMap;

/**
 * @author wyz
 * @create 2021-06-14 23:10
 */
public class Test {
    public static void main(String[] args) throws Exception {
//        String filePath = "D:\\a.txt";
//        StringReadDemo stringReadDemo = new StringReadDemo();
//        DouyinDownload douyinDownload = new DouyinDownload();
//        HashMap<?, ?> getMap=stringReadDemo.Read(filePath);
//
//        for(Object key:getMap.keySet()){
//            System.out.println("key:"+key+" "+"Value:"+getMap.get(key));
//            douyinDownload.inUrl((String) getMap.get(key));
//        }
        DouyinDownload douyinDownload = new DouyinDownload();
        String a = "https://v.douyin.com/e7DgQFa/";
        douyinDownload.inUrl(a);
    }
}
