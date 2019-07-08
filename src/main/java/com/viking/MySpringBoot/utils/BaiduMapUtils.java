package com.viking.MySpringBoot.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Viking on 2019/5/24
 * 百度地图API-地理编码
 */
public class BaiduMapUtils {
    // 对Map内所有value作utf8编码，拼接返回结果
    private static String toQueryString(Map<?, ?> data) throws UnsupportedEncodingException {
        StringBuffer queryString = new StringBuffer();
        for (Map.Entry<?, ?> pair : data.entrySet()) {
            queryString.append(pair.getKey() + "=");
            queryString.append(URLEncoder.encode((String) pair.getValue(),"UTF-8") + "&");
        }
        if (queryString.length() > 0) { queryString.deleteCharAt(queryString.length() - 1); }
        return queryString.toString();
    }
    private static String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {}
        return null;
    }
    private static String getRange(int confidence){
        if (confidence==100)return "解析误差绝对精度小于20m";
        else if (confidence>=90)return "解析误差绝对精度小于50m";
        else if (confidence>=80)return "解析误差绝对精度小于100m";
        else if (confidence>=75)return "解析误差绝对精度小于200m";
        else if (confidence>=70)return "解析误差绝对精度小于300m";
        else if (confidence>=60)return "解析误差绝对精度小于500m";
        else if (confidence>=50)return "解析误差绝对精度小于1000m";
        else if (confidence>=40)return "解析误差绝对精度小于2000m";
        else if (confidence>=30)return "解析误差绝对精度小于5000m";
        else if (confidence>=25)return "解析误差绝对精度小于8000m";
        else if (confidence>=20)return "解析误差绝对精度小于10000m";
        else return "结果有误";
    }
    private static String getSn(String sk,Map<String,String> param)throws UnsupportedEncodingException,NoSuchAlgorithmException {
        String paramsStr = toQueryString(param);
        String wholeStr = "/geocoder/v2/?" + paramsStr + sk;
        String tempStr = URLEncoder.encode(wholeStr, "UTF-8");
        String sn = MD5(tempStr);
        System.out.println(sn);
        return sn;
    }
    @SuppressWarnings("unchecked")
    public static Map<String,Object> getLocation(String ak,String sk,String apad,String city) throws IOException, NoSuchAlgorithmException {
        String url="http://api.map.baidu.com/geocoder/v2/";
//        Map<String,String> param = new LinkedHashMap<>();//Get请求使用
        Map<String,String> param = new TreeMap<>();//Post请求使用
        param.put("address",apad);
        param.put("city",city);
        param.put("ret_coordtype","gcj02ll");
        param.put("output","json");
        param.put("ak",ak);
        String sn = getSn(sk,param);
        param.put("sn",sn);
        HttpClient client  = new HttpClient();
        client.clientPost(url,param);
        Map map = client.getMap();
        System.out.println(client.getStr());
        Map<String,Object> res= new HashMap<>();
        res.put("status",map.get("status"));
        if (Integer.parseInt(map.get("status")+"")!=0) return new HashMap<>();
        Map<String,Object> result = (Map) map.get("result");
        Map<String,Float> location = (Map<String, Float>) result.get("location");
        res.put("经度",location.get("lng"));
        res.put("纬度",location.get("lat"));
        res.put("是否精确",result.get("precise"));
        res.put("误差范围",getRange(Integer.parseInt(result.get("confidence")+"")));
        res.put("理解程度",result.get("comprehension"));
        res.put("地址类型",result.get("level"));
        res.put("坐标",location.get("lng")+","+location.get("lat"));
        System.out.println(res);
        Map<String,Object> bean = new HashMap<>();
        bean.put("lng",location.get("lng"));
        bean.put("lat",location.get("lat"));
        bean.put("precise",result.get("precise"));
        bean.put("confidence",result.get("confidence"));
        bean.put("comprehension",result.get("comprehension"));
        bean.put("level",result.get("level"));
        return  bean;
    }
}
