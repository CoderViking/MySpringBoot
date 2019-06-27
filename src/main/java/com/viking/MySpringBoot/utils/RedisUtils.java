package com.viking.MySpringBoot.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.query.SortQueryBuilder;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by Viking on 2019/6/27
 * redis工具类
 */
@Component
public class RedisUtils<V> {
    @Autowired
    private RedisTemplate<String, V> redisTemplate;


    @PostConstruct
    public void init(){
        redisTemplate.setKeySerializer(new StringRedisSerializer());
    }
    public Object get(final String key){
        return redisTemplate.opsForValue().get(key);
    }
    public void set(final String key, V value){
        redisTemplate.opsForValue().set(key,value);
    }
    public void set(final String key,V value,long seconds){
        redisTemplate.opsForValue().set(key,value,seconds,TimeUnit.SECONDS);
    }
    public void multiSet(final Map<String,V> map){
        redisTemplate.opsForValue().multiSet(map);
    }
    public void reset(final String key, V value){
        redisTemplate.opsForValue().getAndSet(key, value);
    }
    public void delete(final String key){
        redisTemplate.delete(key);
    }
    public void delete(final Collection<String> keys){
        redisTemplate.delete(keys);
    }
    public void deleteByPrefix(String prefix){
        if (!prefix.contains("*")) prefix += "*";
        Set<String> keys = redisTemplate.keys(prefix);
        if (keys != null && !keys.isEmpty()) redisTemplate.delete(keys);
    }
    public boolean exists(final String key){
        Boolean hasKey = redisTemplate.hasKey(key);
        return hasKey != null && hasKey;
    }
    public boolean expire(final String key, Long seconds){
        return expire(key,seconds,TimeUnit.SECONDS);
    }
    public boolean expire(final String key, Long timeout, TimeUnit unit){
        Boolean expire = redisTemplate.expire(key, timeout, unit);
        return expire != null && expire;
    }
    public String type(final String key){
        DataType type = redisTemplate.type(key);
        return type == null ? null : type.code();
    }
    public List<V> sort(String key){
        return redisTemplate.sort(SortQueryBuilder.sort(key).build());
    }



}
