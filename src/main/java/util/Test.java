package util;

import com.sun.xml.internal.ws.util.StringUtils;

import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Properties;

public class Test {

    @org.junit.Test
    public void xml(){
        Properties p = new Properties();
        p.setProperty("id","dean");
        p.setProperty("password","123456");

        try{
            PrintStream fW = new PrintStream(new File("D:\\test1.xml"));
            p.storeToXML(fW,"test");
        } catch (IOException e) {
            e.printStackTrace();
        }}

    @org.junit.Test
    public void aToB(){
        String str = "yideng";
        String capitalize = StringUtils.capitalize(str);
        System.out.println(capitalize); // 输出Yideng
    }
//    https://mp.weixin.qq.com/s?__biz=MzIxNTAwNjA4OQ==&mid=2247521374&idx=2&sn=dfa26da0ea7ab00292e384d6159ab19c&chksm=979c3ab8a0ebb3ae57ae35a1a260470d4ce623f972fe89ba5d600da6a0f6418f7e0be409e8b5&mpshare=1&scene=23&srcid=0730RC5TPI4FqSjN2FRMyMlp&sharer_sharetime=1627653660790&sharer_shareid=423d1586279eb5205fe4e8ab245a3fd3#rd

}