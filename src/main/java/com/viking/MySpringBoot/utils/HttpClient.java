package com.viking.MySpringBoot.utils;

import com.alibaba.druid.support.json.JSONUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* Created by robin on 2016/4/18.
* httpClient 封装
*/
public class HttpClient {
    protected Logger log =  LoggerFactory.getLogger(HttpClient.class);

    //请求响应内容
    private String result;

    /**
     * POST请求
     * @param url  请求地址
     * @param map  请求参数
     * @return
     */
    public HttpClient clientPost(String url,Map map) throws IOException {
        log.info("post request url "+url);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(url);
        httppost.setHeader("ContentType", "application/x-www-form-urlencoded");
        //封装请求参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if(map != null)
            for(Object key:map.keySet()){
                if(map.get(key) != null)
                    nvps.add(new BasicNameValuePair(key.toString(), map.get(key).toString()));
            }
        try {
            UrlEncodedFormEntity entry = new UrlEncodedFormEntity(nvps, "UTF-8");
            entry.setContentType("application/x-www-form-urlencoded;charset=UTF-8");
            httppost.setEntity(entry);
            HttpResponse response = httpClient.execute(httppost);
            HttpEntity entity = response.getEntity();
            if(entity != null) result = EntityUtils.toString(entity, "UTF-8");
        }catch (IOException e){
            throw e;
        }finally {
            httppost.releaseConnection();
        }
        return this;
    }

    /**
     * GET请求
     * @param url  请求地址
     * @param map  请求参数
     * @return
     */
    public HttpClient clientGet(String url,Map map) throws IOException {
        log.info("get request url "+url);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //封装请求地址
        StringBuffer sb = new StringBuffer(url);
        if(map != null){
            sb.append("?");
            for(Object key:map.keySet()){
                sb.append(key);sb.append("=");sb.append(map.get(key));sb.append("&");
            }
        }
        url = sb.toString();
        if(url.contains("&")) url = url.substring(0,url.lastIndexOf("&"));
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse response = httpClient.execute(httpGet);

            HttpEntity entity = response.getEntity();
            if(entity != null) result = EntityUtils.toString(entity,"UTF-8");
        } catch (IOException e){
            throw e;
        }finally{
            httpGet.releaseConnection();
        }
        return this;
    }

    /**
     * 返回请求内容
     * @return  请求内容
     */
    public String getStr(){
        return result;
    }

    /**
     * 返回请求内容以MAP集合形式
     * @return 请求内容MAP集合
     */
    public Map getMap() throws IOException {
        if(result == null) {
            log.error("数据源连接失败");
        }
        return (Map) JSONUtils.parse(result);
    }
}
