package com.viking.MySpringBoot.test;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yanshuai on 2019/7/29
 */
public class TestElasticClient {

    @Test
    public void testClient(){

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));

        IndexRequest indexRequest = new IndexRequest("blog", "java");

        Map<String, Object> blogMap = new HashMap<>();
        blogMap.put("id", "index_07");
        blogMap.put("title", "测试SpringBoot中使用RestHighLevelClient连接es");
        blogMap.put("time", new Date().getTime());
        blogMap.put("mainContent", "主要内容主要内容");
        indexRequest.source(blogMap);
        indexRequest.timeout(TimeValue.timeValueSeconds(1));
        try {
            IndexResponse response = client.index(indexRequest);
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("done");
    }
}
