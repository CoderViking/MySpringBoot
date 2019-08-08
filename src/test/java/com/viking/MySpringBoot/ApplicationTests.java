package com.viking.MySpringBoot;

import com.viking.MySpringBoot.config.restClient.ClientHelper;
import com.viking.MySpringBoot.entity.Blog;
import com.viking.MySpringBoot.utils.RedisUtils;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    private RedisUtils<Object> redisUtils;

    @Test
    public void test(){
        System.out.println(redisUtils.get("key"));
        redisUtils.delete("key");
        System.out.println(redisUtils.get("key"));
        System.out.println(redisUtils.exists("key"));
        List<String> list = new ArrayList<>();
        list.add("HAHAHA");list.add("heiheihei");list.add("hehehe");
        System.out.println(redisUtils.addInSet("mySet","HAHAHA","heiheihei","hehehe","HAHAHA"));
        System.out.println(redisUtils.getInSet("mySet"));
        redisUtils.set("perfix-Key1","value1");
        redisUtils.set("perfix-Key2","value2");
        redisUtils.set("perfix-Key3","value3");
        redisUtils.set("perfix-Key4","value4");
        System.out.println("perfixKeys:"+redisUtils.keys("perfix*"));
        redisUtils.deleteByPrefix("perfix");
        System.out.println("perfixKeys:"+redisUtils.keys("perfix*"));
        List<Object> myList = new ArrayList<>();
        myList.add("listHAHAHA");myList.add("myListHEIEHIEHIEI");myList.add("listHAHAHA");
//        System.out.println("pipeLine:"+redisUtils.pipeLine(myList));
        Long l1 = redisUtils.leftPushAllInList("myListKey", myList);
        Long l2 = redisUtils.leftPushAllInList("myListKey1", "HAHAHA","heiheihei","hehehe","1");
        System.out.println("leftPush:"+redisUtils.rangeInList("myListKey",0,-1));
        System.out.println("leftPush1:"+redisUtils.rangeInList("myListKey1",0,-1));
//        redisUtils.delete("myListKey");
        redisUtils.setExpire("myListKey",5L);
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("leftPush:"+redisUtils.rangeInList("myListKey",0,-1));
        System.out.println("leftPush1:"+redisUtils.rangeInList("myListKey1",0,-1));
        redisUtils.rightPushInList("myList","listHAHAHA");
        redisUtils.rightPushInList("myList","myListHEIEHIEHIEI");
        redisUtils.rightPushInList("myList","listHAHAHA");
        System.out.println("myList:"+redisUtils.rangeInList("myList",0,-1));
    }

    @Test// 测试es的使用，插入数据为例
    public void testClient(){
        RestHighLevelClient client = ClientHelper.getClient();
        IndexRequest indexRequest = new IndexRequest("blog", "java","index_08");

        Map<String, Object> blogMap = new HashMap<>();
        blogMap.put("id", "index_08");
        blogMap.put("title", "测试SpringBoot中使用RestHighLevelClient连接es");
        blogMap.put("time", new Date().getTime());
        blogMap.put("mainContent", "主要内容主要内容");
        indexRequest.source(blogMap);
        indexRequest.timeout(TimeValue.timeValueSeconds(1));
        try {
            IndexResponse response = client.index(indexRequest);
            System.out.println(response);
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("done");
    }

    @Test// 测试es的同步方法增删改查
    public void crudTestInEs() throws IOException {
        RestHighLevelClient client = ClientHelper.getClient();
        Map<String, Object> blogMap = new HashMap<>();
        blogMap.put("id", "index_08");
        blogMap.put("title", "测试SpringBoot中使用RestHighLevelClient连接es");
        blogMap.put("time", new Date().getTime());
        blogMap.put("mainContent", "主要内容主要内容");
        // 增
//        IndexRequest indexRequest = new IndexRequest("指定Index","指定type","指定id");
//        indexRequest.source(blogMap);
//        client.index(indexRequest);
        // 删
//        DeleteRequest deleteRequest = new DeleteRequest("blog", "java","a4J1QGwBjRwxD0F9bvF4");
//        client.delete(deleteRequest);
//        System.out.println("done");
        // 改
//        UpdateRequest updateRequest = new UpdateRequest("指定Index","指定type","指定id");
//        updateRequest.doc(blogMap);
//        client.update(updateRequest);
        // 查
//        GetRequest getRequest = new GetRequest("指定Index","指定type","指定id");
//        getRequest.storedFields(new String[]{});
//        client.get(getRequest);

        SearchRequest searchRequest = new SearchRequest("blog");
        searchRequest.types("java");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();// SearchSourceBuilder使用默认选项创建
//        sourceBuilder.query(QueryBuilders.termQuery("title","FirstPage"));// 设置查询。可以是任何类型的QueryBuilder
//        sourceBuilder.from(0);// 设置from确定结果索引的选项以开始搜索。默认为0。
//        sourceBuilder.size(1);// 设置size确定要返回的搜索匹配数的选项。默认为10。
//        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));// 设置一个可选的超时，控制允许搜索的时间。
//        searchRequest.source(sourceBuilder);// 将SearchSourceBuilder添加到SearchRequest

        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("id","index_04");// 构建查询
//        matchQueryBuilder.fuzziness(Fuzziness.AUTO);// 在匹配查询上启用模糊匹配
        matchQueryBuilder.prefixLength(3);// 在匹配查询上设置前缀长度选项
        matchQueryBuilder.maxExpansions(10);// 设置最大扩展选项以控制查询的模糊过程
        sourceBuilder.query(matchQueryBuilder);
//        sourceBuilder.query(QueryBuilders.matchQuery("title","测试"));
        SearchResponse searchResponse = client.search(searchRequest);
        for (SearchHit hits: searchResponse.getHits().getHits()){
            System.out.println(hits);
            System.out.println(hits.getSourceAsMap());
        }
        System.out.println(searchResponse);
    }
    @Test
    public void crudAsync(){
        RestHighLevelClient client = ClientHelper.getClient();
        // 增
        IndexRequest indexRequest = new IndexRequest("指定Index","指定type","指定id");
        indexRequest.source();
        client.indexAsync(indexRequest, new ActionListener<IndexResponse>() {
            @Override
            public void onResponse(IndexResponse indexResponse) {

            }

            @Override
            public void onFailure(Exception e) {

            }
        });
        // 删
        DeleteRequest deleteRequest = new DeleteRequest("指定Index","指定type","指定id");
        client.deleteAsync(deleteRequest, new ActionListener<DeleteResponse>() {
            @Override
            public void onResponse(DeleteResponse deleteResponse) {

            }

            @Override
            public void onFailure(Exception e) {

            }
        });
        // 改
        UpdateRequest updateRequest = new UpdateRequest("指定Index","指定type","指定id");
        client.updateAsync(updateRequest, new ActionListener<UpdateResponse>() {
            @Override
            public void onResponse(UpdateResponse updateResponse) {

            }

            @Override
            public void onFailure(Exception e) {

            }
        });
        // 查
        GetRequest getRequest = new GetRequest("指定Index","指定type","指定id");
        client.getAsync(getRequest, new ActionListener<GetResponse>() {
            @Override
            public void onResponse(GetResponse documentFields) {

            }

            @Override
            public void onFailure(Exception e) {

            }
        });

        SearchRequest searchRequest = new SearchRequest();
        client.searchAsync(searchRequest, new ActionListener<SearchResponse>() {
            @Override
            public void onResponse(SearchResponse searchResponse) {

            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
