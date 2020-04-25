package com.nahsshan.common.redis.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

/**
 * Redis Key 前缀
 *
 * @author J.zhu
 */
public class RedisKeyUtil {

    /**
     * Redis锁Key 前缀
     */
    public static final String REDIS_LOCKE_KEY_PREFIX = "REDIS_LOCKE_KEY_PREFIX_";

    public static String getLockKey(String key) {
        Assert.isTrue(StringUtils.isNotBlank(key),"Redis Lock Key Can't be Blank");
        return REDIS_LOCKE_KEY_PREFIX + key;
    }
}
