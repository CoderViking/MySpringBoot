package com.viking.MySpringBoot.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.query.SortQueryBuilder;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
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
    /**################################### Basic ###################################################*/
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
    public boolean setExpire(final String key, Long seconds){
        return setExpire(key,seconds,TimeUnit.SECONDS);
    }
    public boolean setExpire(final String key, Long timeout, TimeUnit unit){
        Boolean expire = redisTemplate.expire(key, timeout, unit);
        return expire != null && expire;
    }
    public boolean expireAt(final String key, long timeout){
        Boolean expireAt = redisTemplate.expireAt(key, new Date(timeout));
        return expireAt != null && expireAt;
    }
    public Long getExpire(final String key){
        return redisTemplate.getExpire(key);
    }
    public Long getExpireSeconds(final String key){
        return redisTemplate.getExpire(key,TimeUnit.SECONDS);
    }
    public String type(final String key){
        DataType type = redisTemplate.type(key);
        return type == null ? null : type.code();
    }
    public List<V> sort(String key){
        return redisTemplate.sort(SortQueryBuilder.sort(key).build());
    }
    public String randomKey(){
        return redisTemplate.randomKey();
    }
    /**################################### String ###################################################*/
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
    public Long incrementOne(final String key) {
        return redisTemplate.opsForValue().increment(key, -1L);
    }
    public Long decrease(final String key, long num) {
        return redisTemplate.opsForValue().increment(key, -num);
    }
    public Long increment(final String key, long num) {
        return redisTemplate.opsForValue().increment(key, num);
    }
    public Long strLength(final String key) {
        return redisTemplate.opsForValue().size(key);
    }
    public List<V> strMultiGet(Collection<String> keys) {
        return redisTemplate.opsForValue().multiGet(keys);
    }
    public void setStrMulti(Map<String, V> map) {
        redisTemplate.opsForValue().multiSet(map);
    }
    public void setStrMultiIfAbsent(Map<String, V> map) {
        redisTemplate.opsForValue().multiSetIfAbsent(map);
    }
    public V replaceValue(final String key, V newValue) {
        return redisTemplate.opsForValue().getAndSet(key, newValue);
    }



    /**################################### Hash #####################################################*/
    public void setHash(final String key, String hashKey, V hashValue){
        redisTemplate.opsForHash().put(key,hashKey,hashValue);
    }
    public void setHash(final String key, Map<String,V> map){
        redisTemplate.opsForHash().putAll(key,map);
    }
    public List<Object> hashMultiGet(final String key, Collection<Object> fields){
        return redisTemplate.opsForHash().multiGet(key, fields);
    }
    public Set<Object> hashKeys(final String key){
        return redisTemplate.opsForHash().keys(key);
    }
    public Long hashSize(final String key){
        return redisTemplate.opsForHash().size(key);
    }
    public Long hashIncrement(final String key, V hashKey ,long value){
        return redisTemplate.opsForHash().increment(key,hashKey,value);
    }
    public Double hashIncrement(final String key, V hashKey, double value){
        return redisTemplate.opsForHash().increment(key,hashKey,value);
    }
    public Map<Object, Object> getHash(final String key){
        return redisTemplate.opsForHash().entries(key);
    }
    public boolean hashHasKey(final String key, String hashKey){
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }
    public Object getHashValue(final String key, String hashKey){
        return redisTemplate.opsForHash().get(key,hashKey);
    }
    public long deleteHashValue(final String key, String hashKey){
        return redisTemplate.opsForHash().delete(key,hashKey);
    }
    public long deleteHashValue(final String key, List<String> hashKeys){
        return redisTemplate.opsForHash().delete(key,hashKeys);
    }
    public void putHashElement(final String key, String hashKey, V hashValue){
        redisTemplate.opsForHash().put(key,hashKey,hashValue);
    }
    public List<Object> hashValues(final String key){
        return redisTemplate.opsForHash().values(key);
    }
    public boolean putHashIfAbsent(final String key, String hashKey, Object hashValue) {
        return redisTemplate.<String, Object>opsForHash().putIfAbsent(key, hashKey, hashValue);
    }

    /**################################### other ###################################################*/
    public void quit(){
        redisTemplate.execute((RedisCallback<Object>) redisConnection -> {
            redisConnection.close();
            return null;
        });
    }







    public Boolean setnx(String key, V value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    public void setex(String key, V value, Long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    public void setex(String key, V value, Long offset) {
        redisTemplate.opsForValue().set(key, value, offset);
    }

    public void setbit(final String key, final Long offset, final Boolean value) {
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.setBit(key.getBytes(), offset, value);
                return null;
            }
        });
    }

    public Boolean getbit(final String key, final Long offset) {
        return (Boolean) redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.getBit(key.getBytes(), offset);
            }
        });
    }

    public Integer append(String key, String value) {
        return redisTemplate.opsForValue().append(key, value);
    }

    public void multi() {
        redisTemplate.multi();
    }

    public void unwatch() {
        redisTemplate.unwatch();
    }

    public void discard() {
        redisTemplate.discard();
    }

    public void exec() {
        redisTemplate.exec();
    }

    public void watch(Collection<String> keys) {
        redisTemplate.watch(keys);
    }

    public Long sadd(String key, V value) {
        return redisTemplate.opsForSet().add(key, value);
    }

    public Long scard(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    public Set<V> sdiff(String key, Collection<String> keys) {
        return redisTemplate.opsForSet().difference(key, keys);
    }

    public void sdiffstore(String key, Collection<String> keys, String destinations) {
        redisTemplate.opsForSet().differenceAndStore(key, keys, destinations);
    }

    public Set<V> sinter(String key, Collection<String> keys) {
        return redisTemplate.opsForSet().intersect(key, keys);
    }

    public void sinterstore(String key, Collection<String> keys, String destination) {
        redisTemplate.opsForSet().intersectAndStore(key, keys, destination);
    }

    public Boolean sismember(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    public Set<V> smembers(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    public Boolean smove(String key, V value, String destination) {
        return redisTemplate.opsForSet().move(key, value, destination);
    }

    public Object spop(String key) {
        return redisTemplate.opsForSet().pop(key);
    }

    public Object srandmember(String key) {
        return redisTemplate.opsForSet().randomMember(key);
    }

    public Long srem(String key, Object value) {
        return redisTemplate.opsForSet().remove(key, value);
    }

    public Set<V> sunion(String key, Collection<String> keys) {

        return redisTemplate.opsForSet().union(key, keys);
    }

    public void sunionstore(String key, Collection<String> keys, String destination) {
        redisTemplate.opsForSet().unionAndStore(key, keys, destination);
    }

    public String echo(final String value) {
        return (String) redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                return new String(connection.echo(value.getBytes()));
            }
        });
    }

    public String ping() {
        return (String) redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.ping();
            }
        });
    }

    public void publish(String channel, Object message) {
        redisTemplate.convertAndSend(channel, message);
    }

    public Object lpop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    public Object blpop(String key, Long timeout) {
        return redisTemplate.opsForList().leftPop(key, timeout, TimeUnit.SECONDS);
    }

    public Object brpoplpush(String key, String destination, Long timeout) {
        return redisTemplate.opsForList().rightPopAndLeftPush(key, destination, timeout, TimeUnit.SECONDS);
    }

    public Object rpoplpush(String key, String destination) {
        return redisTemplate.opsForList().rightPopAndLeftPush(key, destination);
    }

    public Object lindex(String key, Long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    public Long linsert(String key, V value, V pivot, String position) {
        if ("BEFORE".equals(position)) {
            return redisTemplate.opsForList().leftPush(key, pivot, value);
        } else if ("AFTER".equals(position)) {
            return redisTemplate.opsForList().rightPush(key, pivot, value);
        } else {
            throw new IllegalArgumentException("Wrong position: " + position);
        }
    }

    public Object rpop(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    public Object brpop(String key, Long timeout) {
        return redisTemplate.opsForList().rightPop(key, timeout, TimeUnit.SECONDS);
    }

    public Long llen(String key) {
        return redisTemplate.opsForList().size(key);
    }

    public List<V> lrange(String key, Long start, Long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    public Long lrem(String key, Object value, Long count) {
        return redisTemplate.opsForList().remove(key, count, value);
    }

    public void lset(String key, V value, Long index) {
        redisTemplate.opsForList().set(key, index, value);
    }

    public void ltrim(String key, Long start, Long end) {
        redisTemplate.opsForList().trim(key, start, end);
    }

    public Long rpush(String key, V value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

    public Long rpushx(String key, V value) {
        return redisTemplate.opsForList().rightPushIfPresent(key, value);
    }

    public Long lpush(String key, V value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    public void del(Collection<String> keys) {
        redisTemplate.delete(keys);
    }
    public void del(String keys) {
        redisTemplate.delete(keys);
    }
    public void delByPrefix(String prefix) {
        if (!prefix.contains("*")) prefix+="*";
        Set keys = redisTemplate.keys(prefix);
        if (!keys.isEmpty()) redisTemplate.delete(keys);
    }

    public Boolean expire(String key, Long timeout) {
        return redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }
    public Boolean expire(String key, Long timeout,TimeUnit type) {
        return redisTemplate.expire(key, timeout, type);
    }
    public Boolean expireat(String key, Long seconds) {
        return redisTemplate.expireAt(key, new Date(seconds * 1000L));
    }

    public Collection<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    public Boolean move(String key, Integer db) {
        return redisTemplate.move(key, db);
    }

    public Boolean persist(String key) {
        return redisTemplate.persist(key);
    }

    public Boolean pexpire(String key, Long timeout) {
        return redisTemplate.expire(key, timeout, TimeUnit.MILLISECONDS);
    }

    public Boolean pexpireat(String key, Long millis) {
        return redisTemplate.expireAt(key, new Date(millis));
    }

    public Object randomkey() {
        return redisTemplate.randomKey();
    }

    public void rename(String key, String value) {
        redisTemplate.rename(key, value);
    }

    public Boolean renamenx(String key, String value) {
        return redisTemplate.renameIfAbsent(key, value);
    }

    public Long ttl(String key) {
        return redisTemplate.getExpire(key);
    }


    public Boolean zadd(String key, V value, Double score) {
        return redisTemplate.opsForZSet().add(key, value, score);
    }

    public Long zcard(String key) {
        return redisTemplate.opsForZSet().size(key);
    }

    public Long zcount(String key, Double min, Double max) {
        return redisTemplate.opsForZSet().count(key, min, max);
    }

    public Double zincrby(String key, V value, Double increment) {
        return redisTemplate.opsForZSet().incrementScore(key, value, increment);
    }

    public void zinterstore(String key, Collection<String> keys, String destination) {
        redisTemplate.opsForZSet().intersectAndStore(key, keys, destination);
    }

    public Object zrange(String key, Long start, Long end, Boolean withScore) {
        if (withScore != null && withScore) {
            return redisTemplate.opsForZSet().rangeWithScores(key, start, end);
        }
        return redisTemplate.opsForZSet().range(key, start, end);
    }

    public Set<V> zrangebyscore(String key, Double min, Double max) {
        return redisTemplate.opsForZSet().rangeByScore(key, min, max);
    }

    public Long zrank(String key, Object value) {
        return redisTemplate.opsForZSet().rank(key, value);
    }

    public Long zrem(String key, Object value) {
        return redisTemplate.opsForZSet().remove(key, value);
    }

    public void zremrangebyrank(String key, Long start, Long end) {
        redisTemplate.opsForZSet().removeRange(key, start, end);
    }

    public void zremrangebyscore(String key, Long start, Long end) {
        redisTemplate.opsForZSet().removeRangeByScore(key, start, end);
    }

    public Object zrevrange(String key, Long start, Long end, Boolean withScore) {
        if (withScore != null && withScore) {
            return redisTemplate.opsForZSet().reverseRangeWithScores(key, start, end);
        }

        return redisTemplate.opsForZSet().reverseRange(key, start, end);
    }

    public Set<V> zrevrangebyscore(String key, Double min, Double max) {
        return redisTemplate.opsForZSet().reverseRangeByScore(key, min, max);
    }

    public Long zrevrank(String key, Object value) {
        return redisTemplate.opsForZSet().reverseRank(key, value);
    }

    public void zunionstore(String key, Collection<String> keys, String destination) {
        redisTemplate.opsForZSet().unionAndStore(key, keys, destination);
    }




}
