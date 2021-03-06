package com.galaxy.galaxyblog.common.utils;

/**
 * TODO 请说明此类的作用
 *
 * @author wangkq
 * @date 2020/10/15
 */
public abstract class RedisCacheUtil {
    //从redis里拿
    @FunctionalInterface
    public interface CacheFactory<K,V>{
        V get(K key);
    }
    //从数据库里拿
    @FunctionalInterface
    public interface RealBaseDataFactory<K,V>{
        V get(K key);
    }
    //刷到缓存
    @FunctionalInterface
    public interface FlushCacheFactory<K,V>{
        void flush(K key, V v);
    }
    //获取缓存的值
    public static <K,V> V get(K k,
                              CacheFactory<K,V> cacheFactory,
                              RealBaseDataFactory<K,V> baseDataFactory,
                              FlushCacheFactory<K,V> flushCacheFactory){
        V v = null;
        v = cacheFactory.get(k);
        if (v != null){
            return v;
        }
        v = baseDataFactory.get(k);
        if (v != null){
            flushCacheFactory.flush(k,v);
        }
        return v;
    }


}
