package com.viking.MySpringBoot;

import com.viking.MySpringBoot.utils.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.lang.Nullable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
        Long l2 = redisUtils.leftPushAllInList("myListKey1", "HAHAHA","heiheihei","hehehe","HAHAHA");
        System.out.println("leftPush:"+redisUtils.rangeInList("myListKey",0,-1));
        System.out.println("leftPush1:"+redisUtils.rangeInList("myListKey1",0,-1));
        redisUtils.delete("myListKey");
        System.out.println("leftPush:"+redisUtils.rangeInList("myListKey",0,-1));
        System.out.println("leftPush1:"+redisUtils.rangeInList("myListKey1",0,-1));
        redisUtils.rightPushInList("myList","listHAHAHA");
        redisUtils.rightPushInList("myList","myListHEIEHIEHIEI");
        redisUtils.rightPushInList("myList","listHAHAHA");
        System.out.println("myList:"+redisUtils.rangeInList("myList",0,redisUtils.sizeInList("myList")));
    }
}
