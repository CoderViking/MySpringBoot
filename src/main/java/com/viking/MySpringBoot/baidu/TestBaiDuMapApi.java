package com.viking.MySpringBoot.baidu;

import com.alibaba.druid.support.json.JSONUtils;
import com.viking.MySpringBoot.utils.HttpClient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by yanshuai on 2019/5/23
 */
public class TestBaiDuMapApi {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
//        String address = "http://api.map.baidu.com/geocoder/v2/?address=北京市海淀区上地十街10号&output=json&ak=您的ak&callback=showLocation";
        String address = "故宫";
        String url="http://api.map.baidu.com/geocoder/v2/";
        String yourak = "";
        String yoursk = "";
        String sn = SnCal.getSn(yourak,yoursk,address);
        HttpClient client  = new HttpClient();
        Map<String,String> param = new LinkedHashMap<>();//GET请求使用
        param.put("address",address);
        param.put("city","成都市");
        param.put("ret_coordtype","gcj02ll");
        param.put("output","json");
        param.put("ak",yourak);
        param.put("sn",sn);
        client.clientGet(url,param);
        Map map = client.getMap();
        System.out.println(map);
        Map m = (Map) ((Map) map.get("result")).get("location");
        Double s = (Double) m.get("lng");//经度
        Double w = (Double) m.get("lat");//纬度
        System.out.println("["+address+"]的坐标为:经度:"+s+"\t纬度:"+w+"\t\t\t"+s+","+w);
        System.out.println();
        String str = client.getStr();
        System.out.println(str);

        /*System.out.println(sn);

        String add = "http://api.map.baidu.com/geocoder/v2/?address="+address+"&output=json&ak="+yourak+"&sn="+sn;
        URL url;
        HttpURLConnection httpurlconnection = null;
        try {
            url = new URL(add);
            //以post方式请求
            httpurlconnection = (HttpURLConnection) url.openConnection();
            httpurlconnection.setDoOutput(true);
            httpurlconnection.setRequestMethod("GET");
//            httpurlconnection.getOutputStream().write(param.getBytes("utf-8"));
            httpurlconnection.getOutputStream().flush();
            httpurlconnection.getOutputStream().close();
            //获取响应代码
            int code = httpurlconnection.getResponseCode();
            String message = httpurlconnection.getResponseMessage();
            System.out.println("响应码:"+code+"\t"+"响应信息:"+message);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
