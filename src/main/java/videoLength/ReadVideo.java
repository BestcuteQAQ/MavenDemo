package videoLength;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;

import java.io.File;

public class ReadVideo {

    public static void main(String[] args){
        File source = new File("C:\\猫哥行测\\猫哥的行测理论课\\");
        Encoder encoder = new Encoder();
        File[] file = source.listFiles();
        long sum =0;
        for (File file2 : file) {
            try {
                 MultimediaInfo m = encoder.getInfo(file2);
                 long ls = m.getDuration()/1000;  //ls是获取到的秒数
                 sum += ls;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        double sum1 = (double)sum;
        double sum2 =sum1/3600;// 转换成为了小时
        System.out.println((int)sum2+"小时"+(int)(sum2%1*60)+"分");
    }
}