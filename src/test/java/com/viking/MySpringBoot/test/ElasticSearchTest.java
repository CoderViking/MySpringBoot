//package com.viking.MySpringBoot.test;
//
//import org.apache.http.HttpHost;
//import org.elasticsearch.action.index.IndexRequest;
//import org.elasticsearch.action.index.IndexResponse;
//import org.elasticsearch.common.unit.TimeValue;
//import org.junit.Test;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by yanshuai on 2019/7/29
// * ES连接测试
// */
//public class ElasticSearchTest {
//
//    @Test
//    public void testClient(){
//
//        RestHighLevelClient client = new RestHighLevelClient(
//                RestClient.builder(
//                        new HttpHost("localhost", 9200, "http")));
//
//        IndexRequest indexRequest = new IndexRequest("blog", "java");
//
//        Map<String, Object> blogMap = new HashMap<>();
//        blogMap.put("id", "index_06");
//        blogMap.put("title", "测试RestHighLevelClient连接es");
//        blogMap.put("time", new Date());
//        blogMap.put("mainContent", "主要内容");
//        indexRequest.source(blogMap);
//        indexRequest.timeout(TimeValue.timeValueSeconds(1));
//        try {
//            IndexResponse response = client.index(indexRequest);
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//        System.out.println("done");
//    }
//}
