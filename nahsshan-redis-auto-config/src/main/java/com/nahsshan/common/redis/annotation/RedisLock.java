package com.nahsshan.common.redis.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解   分布式redis锁
 * 创建时间	    2019年5月9日
 * @author J.zhu
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisLock {

    String description() default "";
    long waitTime() default 1000L;
    long leaseTime() default -1L;
}
