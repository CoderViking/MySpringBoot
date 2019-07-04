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
public class RedisUtils {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


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
    public List<Object> sort(String key){
        return redisTemplate.sort(SortQueryBuilder.sort(key).build());
    }
    public String randomKey(){
        return redisTemplate.randomKey();
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
    public Collection<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }
    public Boolean move(String key, Integer dbIndex) {
        return redisTemplate.move(key, dbIndex);
    }
    public Boolean persist(String key) {
        return redisTemplate.persist(key);
    }
    public void rename(String key, String value) {
        redisTemplate.rename(key, value);
    }
    public Boolean renameIfAbsent(String key, String value) {
        return redisTemplate.renameIfAbsent(key, value);
    }

    /**################################### String ###################################################*/
    public Object get(final String key){
        return redisTemplate.opsForValue().get(key);
    }
    public void set(final String key, Object value){
        redisTemplate.opsForValue().set(key,value);
    }
    public void set(final String key,Object value,long seconds){
        redisTemplate.opsForValue().set(key,value,seconds,TimeUnit.SECONDS);
    }
    public void multiSet(final Map<String,Object> map){
        redisTemplate.opsForValue().multiSet(map);
    }
    public void reset(final String key, Object value){
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
    public List<Object> strMultiGet(Collection<String> keys) {
        return redisTemplate.opsForValue().multiGet(keys);
    }
    public void setStrMulti(Map<String, Object> map) {
        redisTemplate.opsForValue().multiSet(map);
    }
    public void setStrMultiIfAbsent(Map<String, Object> map) {
        redisTemplate.opsForValue().multiSetIfAbsent(map);
    }
    public Object replaceValue(final String key, Object newValue) {
        return redisTemplate.opsForValue().getAndSet(key, newValue);
    }
    public boolean setStrIfAbsent(final String key, Object value) {
        Boolean setIfAbsent = redisTemplate.opsForValue().setIfAbsent(key, value);
        return setIfAbsent != null && setIfAbsent;
    }
    public Integer append(String key, String value) {
        return redisTemplate.opsForValue().append(key, value);
    }

    /**################################### Hash #####################################################*/
    public void setHash(final String key, String hashKey, Object hashValue){
        redisTemplate.opsForHash().put(key,hashKey,hashValue);
    }
    public void setHash(final String key, Map<String,Object> map){
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
    public Long hashIncrement(final String key, Object hashKey ,long value){
        return redisTemplate.opsForHash().increment(key,hashKey,value);
    }
    public Double hashIncrement(final String key, Object hashKey, double value){
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
    public void putHashElement(final String key, String hashKey, Object hashValue){
        redisTemplate.opsForHash().put(key,hashKey,hashValue);
    }
    public List<Object> hashValues(final String key){
        return redisTemplate.opsForHash().values(key);
    }
    public boolean putHashIfAbsent(final String key, String hashKey, Object hashValue) {
        return redisTemplate.<String, Object>opsForHash().putIfAbsent(key, hashKey, hashValue);
    }

    /**################################### Set ###################################################*/
    @SuppressWarnings("unchecked")
    public Long addInSet(String key, Object ... value) {
        return  redisTemplate.opsForSet().add(key, value);
    }
    public Set<Object> getInSet(final String key){
        return redisTemplate.opsForSet().members(key);
    }
    public Long sizeInSet(String key) {
        return redisTemplate.opsForSet().size(key);
    }
    /**
     * 当前key的set集合和其他otherKeys的set集合中元素的不同集合
     * @param key 当前key
     * @param otherKeys 其他keys
     * @return 不同的set集合
     */
    public Set<Object> differenceInSet(String key, Collection<String> otherKeys) {
        return redisTemplate.opsForSet().difference(key, otherKeys);
    }
    /**
     * 当前key的set集合和其他otherKeys的set集合中元素的不同集合并存入另目标destKey中
     * @param key 当前key
     * @param otherKeys 其他keys
     * @param destKey 目标key
     * @return 目标set中元素的个数
     */
    public Long differenceAndStoreInSet(String key, Collection<String> otherKeys, String destKey) {
        return redisTemplate.opsForSet().differenceAndStore(key, otherKeys, destKey);
    }
    /**
     * 当前key的set集合和其他otherKeys的set集合中元素的相交集合
     * @param key 当前key
     * @param otherKeys 其他keys
     * @return 相交集合
     */
    public Set<Object> intersectInSet(String key, Collection<String> otherKeys) {
        return redisTemplate.opsForSet().intersect(key, otherKeys);
    }
    /**
     * 当前key的set集合和其他otherKeys的set集合中元素的相同集合并存入另目标destKey中
     * @param key 当前key
     * @param otherKeys 其他keys
     * @param destKey 目标key
     * @return 目标set中元素的个数
     */
    public Long intersectAndStoreInSet(String key, Collection<String> otherKeys, String destKey) {
         return redisTemplate.opsForSet().intersectAndStore(key, otherKeys, destKey);
    }
    public Boolean isMemberInSet(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }
    public Set<Object> elementsInSet(String key) {
        return redisTemplate.opsForSet().members(key);
    }
    public Boolean moveToDestInSet(String key, Object value, String destKey) {
        return redisTemplate.opsForSet().move(key, value, destKey);
    }
    public Object popInSet(String key) {
        return redisTemplate.opsForSet().pop(key);
    }
    public Object randomElementInSet(String key) {
        return redisTemplate.opsForSet().randomMember(key);
    }
    public Long deleteInSet(String key, Object value) {
        return redisTemplate.opsForSet().remove(key, value);
    }
    public Set<Object> unionInSet(String key, Collection<String> otherKeys) {
        return redisTemplate.opsForSet().union(key, otherKeys);
    }
    public void unionAndStoreInSet(String key, Collection<String> otherKeys, String destKey) {
        redisTemplate.opsForSet().unionAndStore(key, otherKeys, destKey);
    }

    /**################################### Zset ###################################################*/
    public Boolean addInZset(String key, Object value, Double score) {
        return redisTemplate.opsForZSet().add(key, value, score);
    }
    public Long sizeInZset(String key) {
        return redisTemplate.opsForZSet().size(key);
    }
    public Long countInZset(String key, Double min, Double max) {
        return redisTemplate.opsForZSet().count(key, min, max);
    }
    public Double incrementScoreInZset(String key, Object value, Double increment) {
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

    public Set<Object> zrangebyscore(String key, Double min, Double max) {
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

    public Set<Object> zrevrangebyscore(String key, Double min, Double max) {
        return redisTemplate.opsForZSet().reverseRangeByScore(key, min, max);
    }

    public Long zrevrank(String key, Object value) {
        return redisTemplate.opsForZSet().reverseRank(key, value);
    }

    public void zunionstore(String key, Collection<String> keys, String destination) {
        redisTemplate.opsForZSet().unionAndStore(key, keys, destination);
    }
    /**################################### List ###################################################*/
    public Object leftPopInList(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }
    public Object leftPopInList(String key, Long timeout) {
        return redisTemplate.opsForList().leftPop(key, timeout, TimeUnit.SECONDS);
    }
    public Object rightPopAndLeftPushInLis(String key, String destKey, Long timeout) {
        return redisTemplate.opsForList().rightPopAndLeftPush(key, destKey, timeout, TimeUnit.SECONDS);
    }
    public Object rightPopAndLeftPushInList(String key, String destKey) {
        return redisTemplate.opsForList().rightPopAndLeftPush(key, destKey);
    }
    public Object indexInList(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }
    public Long linsert(String key, Object value, Object pivot, boolean isBefore) {
        if (isBefore) {
            return redisTemplate.opsForList().leftPush(key, pivot, value);
        } else{
            return redisTemplate.opsForList().rightPush(key, pivot, value);
        }
    }
    public Object rightPopInList(final String key) {
        return redisTemplate.opsForList().rightPop(key);
    }
    public Object rightPopInList(String key, long timeout) {
        return redisTemplate.opsForList().rightPop(key, timeout, TimeUnit.SECONDS);
    }
    public Long sizeInList(String key) {
        return redisTemplate.opsForList().size(key);
    }
    public List<Object> rangeInList(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }
    public Long removeInList(String key, Object value, Long count) {
        return redisTemplate.opsForList().remove(key, count, value);
    }
    public void setInList(String key, Object value, Long index) {
        redisTemplate.opsForList().set(key, index, value);
    }
    public void trimInList(String key, Long start, Long end) {
        redisTemplate.opsForList().trim(key, start, end);
    }
    public Long rightPushInList(String key, Object value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }
    public Long rightPushIfPresentInList(String key, Object value) {
        return redisTemplate.opsForList().rightPushIfPresent(key, value);
    }
    public Long leftPushInList(String key, Object value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    /**################################### other ###################################################*/
    public void quit(){
        redisTemplate.execute((RedisCallback<Object>) redisConnection -> {
            redisConnection.close();
            return null;
        });
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

    public Boolean pexpire(String key, Long timeout) {
        return redisTemplate.expire(key, timeout, TimeUnit.MILLISECONDS);
    }










}
