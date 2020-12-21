package com.galaxy.galaxyblog.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * TODO 请说明此类的作用
 *
 * @author wangkq
 * @date 2019/12/6
 */
@Component
public final class RedisUtil {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public RedisUtil() {
    }

    public RedisUtil(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 设置缓存期限
     * @param key
     * @param time
     * @return
     */
    public boolean expire(String key ,long time){
        try {
            if (time>0){
                redisTemplate.expire(key,time, TimeUnit.SECONDS);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 返回key期限
     * @param key
     * @return
     */
    public long getExpire(String key){
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 查看key存在与否
     * @param key
     * @return
     */
    public boolean hasKey(String key){
        try {
            return redisTemplate.hasKey(key);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除缓存
     * @param key
     * @return
     */
    public boolean del(String... key){
        try {
            if (key != null && key.length == 1){
                redisTemplate.delete(key[0]);
            }else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 普通存储获取
     * @param key
     * @return
     */
    public Object get(String key){
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通存储存入
     * @param key
     * @param obj
     * @return
     */
    public boolean set(String key ,Object obj){
        try {
            redisTemplate.opsForValue().set(key, obj);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 普通缓存存入 并设置期限
     * @param key
     * @param obj
     * @param time  time 时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return  T/F
     */
    public boolean set(String key ,Object obj, long time){
        try {
            redisTemplate.opsForValue().set(key, obj, time);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 递增
     * @param key
     * @param delta 递增因子
     * @return
     */
    public long incr(String key, long delta){
        if (delta<0){
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 递减
     * @param key
     * @param delta 递减因子
     * @return
     */
    public long decr(String key, long delta){
        if (delta<0){
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    /**
     * hashGet
     * @param key
     * @param item
     * @return
     */
    public Object hget(String key, String item){
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 获取hashkey 所有键值
     * @param key
     * @return
     */
    public Map<Object, Object> hmget(String key){
        return  redisTemplate.opsForHash().entries(key);
    }

    /**
     * hashset
     * @param key
     * @param map
     * @return
     */
    public boolean hmset(String key, Map<String, ? extends Object> map){
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    /**
     * hastset 并设置期限
     * @param key
     * @param map
     * @param time
     * @return
     */
    public boolean hmset(String key, Map<String, Object> map, long time){
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time>0){
                expire(key, time);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     * @param key
     * @param item
     * @param value
     * @return
     */
    public boolean hset(String key, Object item, Object value){
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return  true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据并设置期限,如果不存在将创建
     * @param key
     * @param item
     * @param value
     * @param time
     * @return
     */
    public boolean hset(String key, Object item, Object value, long time){
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time>0){
                expire(key, time);
            }
            return  true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除hash
     * @param key
     * @param item
     */
    public void hdel(String key, Object item){
        redisTemplate.opsForHash().delete(key, item);
    }
    /**
     * 判断hash表中是否有该项的值
     * @param key 键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }
    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     * @param key 键
     * @param item 项
     * @param by 要增加几(大于0)
     * @return
     */
    public double hincr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }
    /**
     * hash递减
     * @param key 键
     * @param item 项
     * @param by 要减少记(小于0)
     * @return
     */
    public double hdecr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }
    // ============================set=============================
    /**
     * 根据key获取Set中的所有值
     * @param key 键
     * @return
     */
    public Set<Object> sGet(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 根据value从一个set中查询,是否存在
     * @param key 键
     * @param value 值
     * @return true 存在 false不存在
     */
    public boolean sHasKey(String key, Object value) {
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 将数据放入set缓存
     * @param key 键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSet(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    /**
     * 将set数据放入缓存
     * @param key 键
     * @param time 时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSetAndTime(String key, long time, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().add(key, values);
            if (time > 0)
                expire(key, time);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    /**
     * 获取set缓存的长度
     * @param key 键
     * @return
     */
    public long sGetSetSize(String key) {
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    /**
     * 移除值为value的
     * @param key 键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public long setRemove(String key, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().remove(key, values);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    // ===============================list=================================
    /**
     * 获取list缓存的内容
     * @param key 键
     * @param start 开始
     * @param end 结束 0 到 -1代表所有值
     * @return
     */
    public List<Object> lGet(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 获取list缓存的长度
     * @param key 键
     * @return
     */
    public long lGetListSize(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    /**
     * 通过索引 获取list中的值
     * @param key 键
     * @param index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return
     */
    public Object lGetIndex(String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 将list放入缓存
     * @param key 键
     * @param value 值
     * @return
     */
    public boolean lSet(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 将list放入缓存
     * @param key 键
     * @param value 值
     * @param time 时间(秒)
     * @return
     */
    public boolean lSet(String key, Object value, long time) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0)
                expire(key, time);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 将list放入缓存
     * @param key 键
     * @param value 值
     * @return
     */
    public boolean lSet(String key, List<Object> value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 将list放入缓存
     * @param key 键
     * @param value 值
     * @param time 时间(秒)
     * @return
     */
    public boolean lSet(String key, List<Object> value, long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0)
                expire(key, time);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 根据索引修改list中的某条数据
     * @param key 键
     * @param index 索引
     * @param value 值
     * @return
     */
    public boolean lUpdateIndex(String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 移除N个值为value
     * @param key 键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public long lRemove(String key, long count, Object value) {
        try {
            Long remove = redisTemplate.opsForList().remove(key, count, value);
            return remove;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获得key数组里面key2元素的索引
     * @param key
     * @param key2
     * @return
     */
    public Long rank(String key, Object key2) {
        return redisTemplate.opsForZSet().rank(key, key2);
    }
    /**
     * 有序集合添加
     * @param key
     * @param value
     * @param scoure
     */
    public void zAdd(String key, Object value, double scoure) {
        redisTemplate.opsForZSet().add(key, value, scoure);
    }
    /**
     * 获得key数组里面key2元素的排序值
     * @param key
     * @param key2
     * @return
     */
    public Double score(String key, Object key2) {
        return redisTemplate.opsForZSet().score(key, key2);
    }
    /**
     * 从高到低的排序集中获取从头(start)到尾(end)内的元素。
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<Object> reverseRange(String key, long start, long end) {
        return redisTemplate.opsForZSet().reverseRange(key, start, end);
    }

    /**
     * 从有序集合中获取元素（无序）
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<Object> zRange(String key , long start, long end){
        return redisTemplate.opsForZSet().range(key, start, end);
    }

    /**
     * 返回按分值排名范围内的有序集合成员，从大到小
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<Object> zRevRange(String key, long start, long end){
        return redisTemplate.opsForZSet().reverseRange(key, start, end);
    }
    /**
     * 返回按分值排名范围内的有序集合成员和分值，从大到小
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<ZSetOperations.TypedTuple<Object>> zRevRangeWithScores(String key, long start, long end){
        return redisTemplate.opsForZSet().reverseRangeWithScores(key, start, end);
    }

    /**
     * 删除有序集合
     * @param key
     * @param item
     * @return
     */
    public Long zRem(String key, String item){
        return redisTemplate.opsForZSet().remove(key, item);
    }
    /**
     * 根据分数保留指定个数，其余的元素删除
     * @param key
     * @param number
     * @return
     */
    public Boolean remove(String key, Integer number) {
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        Long size = zset.size(key);
        if (size > number) {
            //获取变量指定区间的元素
            Set<ZSetOperations.TypedTuple<Object>> typedTuples = zset.rangeWithScores(key, 0, (size - 1) - number);
            Set set = new HashSet();
            for (ZSetOperations.TypedTuple<Object> o : typedTuples) {
                set.add(o.getValue());
            }
            for (Object o : set) {
                Long aLong = zset.remove(key, o);
                if (aLong == null) {
                    return false;
                }
            }
            return true;
        }else {
            return true;
        }
    }


}
