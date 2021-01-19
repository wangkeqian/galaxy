package com.galaxy.galaxyblog.common.utils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * TODO 请说明此类的作用
 *
 * @author wangkq
 * @date 2021/1/19
 */
public class CommonUtils {

    /**
     * 获取时间戳
     * @return
     */
    public static long getLocalTimestamp(){
        ZoneOffset zoneOffset=ZoneOffset.ofHours(8);
        LocalDateTime localDateTime=LocalDateTime.now();
        return localDateTime.toEpochSecond(zoneOffset);
    }
}
