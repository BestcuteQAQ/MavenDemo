package com.DouYin.test;

import cn.hutool.core.map.MapUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
/**
 *  通过分享链接下载无水印视频
 */
class DouyinDownload {

    public static void inUrl (String url) throws Exception {
//        String url = "https://v.douyin.com/exnrUMA/";
//        String url = "https://v.douyin.com/extVyY6/";
        final String videoPath = "https://www.iesdouyin.com/web/api/v2/aweme/iteminfo/?item_ids=";
        url = decodeHttpUrl(url); //过滤链接，获取http连接地址
        Connection con = Jsoup.connect(url);

        con.header("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1");
        Connection.Response resp = con.method(Connection.Method.GET).execute();
        String strUrl = resp.url().toString();
        String itemId = strUrl.substring(strUrl.indexOf("video/"), strUrl.lastIndexOf("/")).replace("video/", "");
        String videoUrl = videoPath + itemId;
        String jsonStr = Jsoup.connect(videoUrl).ignoreContentType(true).execute().body();
        JSONObject json = new JSONObject(jsonStr);

        String videoAddress = json.getJSONArray("item_list").getJSONObject(0).getJSONObject("video").getJSONObject("play_addr").getJSONArray("url_list").get(0).toString();
        String desc = json.getJSONArray("item_list").getJSONObject(0).getStr("desc");
        System.out.println("抖音描述："+desc);

        HashMap headers = MapUtil.newHashMap();
        headers.put("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1");
        String replace = videoAddress.replace("playwm", "play"); //替换成无水印链接
        System.out.println(replace);
        HttpResponse execute = HttpUtil.createGet(replace).addHeaders(headers).execute();

        String finalVideoAddress = execute.header("Location");
        //下载到指定文件路径
        downloadVideo(finalVideoAddress,desc);
    }

    /**
     * 过滤链接，获取http连接地址
     * @param url
     * @return
     */
    public static String decodeHttpUrl(String url) {
        int start = url.indexOf("http");
        int end = url.lastIndexOf("/");
        String decodeurl = url.substring(start, end);
        return decodeurl;
    }

    /**
     * 下载
     * @param videoAddress
     * @param desc
     */
    private static void downloadVideo(String videoAddress,String desc) {
        int byteRead;
        try {
            URL url = new URL(videoAddress);
            //获取链接
            URLConnection conn = url.openConnection();
            //输入流
            InputStream inStream = conn.getInputStream();
            //封装一个保存文件的路径对象
            File fileSavePath = new File("D:/douyin/"+desc+".mp4");
            //注:如果保存文件夹不存在,那么则创建该文件夹
            File fileParent = fileSavePath.getParentFile();
            if (!fileParent.exists()) {
                fileParent.mkdirs();
            }
            if(fileSavePath.exists()){ //如果文件存在，则删除原来的文件
                fileSavePath.delete();
            }
            //写入文件
            FileOutputStream fs = new FileOutputStream(fileSavePath);
            byte[] buffer = new byte[1024];
            while ((byteRead = inStream.read(buffer)) != -1) {
                fs.write(buffer, 0, byteRead);
            }
            inStream.close();
            fs.close();
            System.out.println("\n-----视频保存路径-----\n" + fileSavePath.getAbsolutePath());
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }
}
