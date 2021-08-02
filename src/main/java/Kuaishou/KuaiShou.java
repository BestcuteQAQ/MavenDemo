package Kuaishou;

import cn.hutool.core.map.MapUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
//import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
 
/**
 * 快手去水印
 *
 * @author taishan
 * @version 1.0
 * @date 2020/8/4
 * @since JDK1.8
 */
public class KuaiShou {

//    private static Logger logger=Logger.getLogger(KuaiShou.class);
 
    //视频保存目录
    private static final String videoSavePath="D:\\快手视频";
 
    //分享链接（手动修改）
    private static String targetPath = "https://v.kuaishou.com/aRninw ";
 
    public static void main(String[] args) {
        ksParseUrl(targetPath);
    }

    /**
     * 方法描述: 解析下载视频
     *
     * @param url
     * @author tarzan
     * @date 2020年08月04日 10:33:40
     */
        public static void ksParseUrl(String url) {
        HashMap<String, String> headers = MapUtil.newHashMap();
        headers.put("User-Agent", "Mozilla/5.0 (Linux; Android 5.0; SM-G900P Build/LRX21T) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Mobile Safari/537.36");
        String redirectUrl = HttpUtil.createGet(url).addHeaders(headers).execute().header("Location");
        String body= HttpUtil.createGet(redirectUrl).addHeaders(headers).execute().body();
        Document doc= Jsoup.parse(body);
        Elements videoElement = doc.select("script[type=text/javascript]");
        String videoInfo = videoElement.get(3).data().replaceAll("window.pageData= ","");
        JSONObject json =JSONObject.parseObject(videoInfo);
        String title = json.getJSONObject("video").getString("caption");
        String videoUrl=json.getJSONObject("video").getString("srcNoMark");
        videoUrl=videoUrl.substring(0,videoUrl.indexOf("?"));
//        logger.debug(videoUrl);
//        logger.debug(title);
        downVideo(videoUrl,videoSavePath);
    }
 
 
    /**
     * 方法描述: 下载视频到本地
     *
     * @param httpUrl
     * @param saveFile
     * @author tarzan
     * @date 2020年08月04日 10:34:09
     */
    public static void downVideo(String httpUrl, String saveFile) {
        int byteRead;
        try {
            URL url = new URL(httpUrl);
            //获取链接
            URLConnection conn = url.openConnection();
            //输入流
            InputStream inStream = conn.getInputStream();
            //封装一个保存文件的路径对象
            File fileSavePath = new File(saveFile);
            //注:如果保存文件夹不存在,那么则创建该文件夹
            File fileParent = fileSavePath.getParentFile();
            if(!fileParent.exists()){
                fileParent.mkdirs();
            }
            //写入文件
            FileOutputStream fs = new FileOutputStream(fileSavePath);
            byte[] buffer = new byte[1024];
            while ((byteRead = inStream.read(buffer)) != -1) {
                fs.write(buffer, 0, byteRead);
            }
            inStream.close();
            fs.close();
            System.out.println("\n-----视频保存路径-----\n"+fileSavePath.getAbsolutePath());
        } catch (FileNotFoundException e) {
//            logger.error(e.getMessage());
        } catch (IOException e) {
//            logger.error(e.getMessage());
        }
    }
 
    /**
     * 方法描述: 过滤分享链接的中文汉字
     *
     * @param url
     * @Return {@link String}
     * @author tarzan
     * @date 2020年08月03日 17:36:33
     */
    public static String filterUrl(String url) {
        String regex = "https?://(\\w|-)+(\\.(\\w|-)+)+(/(\\w+(\\?(\\w+=(\\w|%|-)*(\\&\\w+=(\\w|%|-)*)*)?)?)?)+";//匹配网址
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(targetPath);
        if(m.find()){
          return   targetPath.substring(m.start(),m.end());
        }
        return "";
    }
 
}