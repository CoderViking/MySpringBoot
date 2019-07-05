package com.viking.MySpringBoot.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.viking.MySpringBoot.exception.SimpleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by Viking on 2019/6/19
 * 微信公众号工具类
 */
@Component
public class WeChatUtil {

    private static Logger log = LoggerFactory.getLogger(WeChatUtil.class);

    public static String APPID;
    public static String APPSECRET;
    public static String REDIRECT_URL;
    public static String TOKEN;
    public static String SIGNATURE_TYPE;
    private static String GET_WEB_ACCESS_TOKEN_URL;
    private static String GET_USER_INFO_URL;
    private static String GET_ACCESS_TOKEN_URL;
    private static String GET_TICKET_URL;
    private static String GET_SUBSCRIBE_URL;

    @Value("${wechat.appid}")
    private void setAppId(String appid){
        APPID = appid;
    }
    @Value("${wechat.appsecret}")
    private void setAppSecret(String appsecret){
        APPSECRET = appsecret;
    }
    @Value("${wechat.redirect-url}")
    private void setRedirectUrl(String redirectUrl){
        REDIRECT_URL = redirectUrl;
    }
    @Value("${wechat.token}")
    private void setToken(String token){
        TOKEN = token;
    }
    @Value("${wechat.signature-type}")
    private void setSignatureType(String signatureType){
        SIGNATURE_TYPE = signatureType;
    }
    @Value("${wechat.get-web-access-token-url}")
    private void setGetWebAccessTokenUrl(String getWebAccessTokenUrl){
        GET_WEB_ACCESS_TOKEN_URL = getWebAccessTokenUrl;
    }
    @Value("${wechat.get-user-info-url}")
    private void setGetUserInfoUrl(String getUserInfoUrl){
        GET_USER_INFO_URL = getUserInfoUrl;
    }
    @Value("${wechat.get-access-token-url}")
    private void setGetAccesstokenUrl(String getAccesstokenUrl){
        GET_ACCESS_TOKEN_URL = getAccesstokenUrl;
    }
    @Value("${wechat.get-ticket-url}")
    private void setGetTicketUrl(String getTicketUrl){
        GET_TICKET_URL = getTicketUrl;
    }
    @Value("${wechat.get-subscribe-url}")
    private void setGetSubscribeUrl(String getSubscribeUrl){
        GET_SUBSCRIBE_URL = getSubscribeUrl;
    }



    /******************************************** 微信网页授权部分 ******************************************************/

    /**
     * 获取网页授权的AccessToken凭据
     * @param code 授权码
     * @return 返回值json包
     */
    public static JSONObject getWebAccessToken(String code) {
        String result = null;
        try {
            log.info("请求链接:"+GET_WEB_ACCESS_TOKEN_URL.replace("APPID", APPID).replace("SECRET", APPSECRET).replace("CODE", code));
            result = HttpRequestUtil.doGet(GET_WEB_ACCESS_TOKEN_URL.replace("APPID", APPID).replace("SECRET", APPSECRET).replace("CODE", code));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(result);
    }

    /**
     * 获取用户信息
     * @param accessToken 网页授权access_token
     * @param openId 微信用户openid
     * @return 返回值json包
     */
    public static JSONObject getUserInfo(String accessToken,String openId) {
        String result = null;
        try {
            result = HttpRequestUtil.doGet(GET_USER_INFO_URL.replace("ACCESS_TOKEN", accessToken).replace("OPENID",openId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(result);
    }

    /**
     * 获取微信用户是否订阅公众号
     * @param accessToken token
     * @param openId openid
     * @return 微信服务器返回值
     */
    public static JSONObject checkSubscribe(String accessToken, String openId){
        String result = null;
        try {
            result = HttpRequestUtil.doGet(GET_SUBSCRIBE_URL.replace("ACCESS_TOKEN", accessToken).replace("OPENID",openId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(result);
    }

    /*********************************** 生成前端调用js接口的signature部分 ***********************************************/
    //缓存的access_token
    private static String accessToken;
    //access_token的失效时间
    private static long expiresTime;
    /**
     * 获取accessToken
     * @return accessToken
     */
    private static String getAccessToken() throws Exception {
        //判断accessToken是否已经过期，如果过期需要重新获取
        if(accessToken==null||expiresTime < new Date().getTime()){
            //发起请求获取accessToken
            String result = HttpRequestUtil.doGet(GET_ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET));
            //把json字符串转换为json对象
            JSONObject json = JSON.parseObject(result);
            System.out.println("----获取access_token:"+json);
            //缓存accessToken
            accessToken = json.getString("access_token");
            //设置accessToken的失效时间
            long expires_in = json.getLong("expires_in");
            //失效时间 = 当前时间 + 有效期(提前一分钟)
            expiresTime = new Date().getTime()+ (expires_in-60) * 1000;
        }
        return accessToken;
    }

    /**
     * 获取JSSDK的jsapi_ticket
     * @param accessToken toekn
     * @return ticket
     */
    private static String getJsapi_ticket(String accessToken) throws Exception {
        //发起请求到指定的接口
        String result = HttpRequestUtil.doGet(GET_TICKET_URL.replace("ACCESS_TOKEN",accessToken));
        JSONObject jsonObject = JSONObject.parseObject(result);
        log.info("----获取jsapi_ticket:"+jsonObject);
        return jsonObject.getString("ticket");
    }

    /**
     * 计算前端调用js接口jssdk-config的signature签名
     * @param jsapi_ticket jsapi_ticket
     * @param timestamp 时间戳
     * @param noncestr 随机数
     * @param url 网页路径
     * @return 签名
     */
    private static String getSignature(String jsapi_ticket,Long timestamp,String noncestr,String url ){
        //对所有待签名参数按照字段名的ASCII 码从小到大排序（字典序）
        Map<String,Object> map = new TreeMap<>();
        map.put("jsapi_ticket",jsapi_ticket);
        map.put("timestamp",timestamp);
        map.put("noncestr",noncestr);
        map.put("url",url);
        //使用URL键值对的格式（即key1=value1&key2=value2…）拼接成字符串string1
        StringBuilder sb = new StringBuilder();
        Set<String> set = map.keySet();
        for (String key : set) {
            sb.append(key).append("=").append(map.get(key)).append("&");
        }
        //去掉最后一个&符号
        String temp = sb.substring(0,sb.length()-1);
        //使用sha1加密
        String signature = SecurityUtil.SHA1(temp);
        return signature;
    }
    public static Map<String,Object> getTokenAndTicket(){
        try {
            String accessToken = getAccessToken();
            String ticket = getJsapi_ticket(accessToken);
            Map<String,Object> res = new HashMap<>();
            res.put("token",accessToken);
            res.put("ticket",ticket);
            res.put("expiresTime",expiresTime);
            log.info("----获取token和ticket成功:"+res);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new SimpleException("获取access_token失败");
    }
    public static Map<String,Object> createSignature(String ticket, String url){
        try {
            Long timeStamp = new Date().getTime();
            String noncestr = UUID.randomUUID().toString().replace("-","").toLowerCase();
            String signature = getSignature(ticket, timeStamp, noncestr, url);
            Map<String,Object> res = new HashMap<>();
            res.put("timeStamp",timeStamp);
            res.put("noncestr",noncestr);
            res.put("signature",signature);
            res.put("expiresTime",expiresTime);
            log.info("----生成签名成功:"+res);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new SimpleException("签名生成失败");
    }
}
