package com.viking.MySpringBoot;

import com.viking.MySpringBoot.utils.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
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
        System.out.println(redisUtils.addInSet("mySet","HAHAHA","heiheihei","hehehe"));
        System.out.println(redisUtils.getInSet("mySet"));
    }
}
