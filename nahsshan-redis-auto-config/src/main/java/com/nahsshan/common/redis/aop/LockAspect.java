package com.nahsshan.common.redis.aop;

import com.nahsshan.common.redis.annotation.LockKey;
import com.nahsshan.common.redis.annotation.RedisLock;
import com.nahsshan.common.redis.utils.RedisKeyUtil;
import com.nahsshan.common.redis.utils.RedissonLockUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author J.zhu
 */
@Slf4j
@Aspect
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class LockAspect {
    /**
     * 思考：为什么不用synchronized
     * service 默认是单例的，并发下lock只有一个实例
     * 互斥锁 参数默认false，不公平锁
     */
    private static Lock lock = new ReentrantLock(true);

    /**
     * Service层切点
     */
    @Pointcut("@annotation(com.nahsshan.common.redis.annotation.RedisLock)")
    public void redisLockAspect() {

    }

    @Around("redisLockAspect()")
    public Object redisLockAround(ProceedingJoinPoint joinPoint) {
        Object obj = null;
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        RedisLock annotation = method.getAnnotation(RedisLock.class);

        String lockKey = getLockKey(joinPoint.getArgs(), method.getParameters());

        boolean res = false;
        try {
            res = RedissonLockUtil.tryLock(lockKey, TimeUnit.MILLISECONDS, annotation.waitTime(), annotation.leaseTime());
            if (res) {
                log.info("RedisLock 分布式锁获取成功 description: {} lockKey：{}", annotation.description(),lockKey);
                obj = joinPoint.proceed();
            }else {
                log.error("RedisLock 分布式锁获取失败 description: {}",annotation.description());
                throw new RuntimeException("RedisLock 分布式锁获取失败 description: " + annotation.description());
            }
        } catch (Throwable e) {
            log.error("RedisLock 分布式锁获取失败", e);
            throw new RuntimeException("RedisLock 分布式锁获取失败 description: " + annotation.description());
        } finally {
            if (res) {
                log.info("RedisLock 释放锁 description: {}, lockKey：{}", annotation.description(), lockKey);
                RedissonLockUtil.unlock(lockKey);
            }
        }
        return obj;
    }

    /**
     * 判断一个对象是否是基本类型或基本类型的封装类型
     */
    private boolean isPrimitive(Object obj) {
        try {
            return ((Class<?>) obj.getClass().getField("TYPE").get(null)).isPrimitive();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @param args
     * @param parameters
     * @param description
     * @return
     */
    private String getLockKey(Object[] args, Parameter[] parameters) {

        // 获取方法上的RedisLock注解
        for (Parameter parameter : parameters) {
            LockKey lockKey = parameter.getAnnotation(LockKey.class);
            int paramIndex = ArrayUtils.indexOf(parameters, parameter);
            if (lockKey != null) {
                if (isPrimitive(args[paramIndex])) {
                    // 是基本数据类型且参数名称与注解中相同
                    return RedisKeyUtil.getLockKey(String.valueOf(args[paramIndex]));
                } else {
                    String fieldName = lockKey.fieldName();
                    Field[] fields = args[paramIndex].getClass().getDeclaredFields();
                    for (Field field : fields) {
                        if (field.getName().equals(fieldName)) {
                            field.setAccessible(true);
                            try {
                                return RedisKeyUtil.getLockKey(String.valueOf(field.get(parameter)));
                            } catch (IllegalAccessException e) {
                                log.error("RedisLock 分布式锁获取失败", e);
                                throw new RuntimeException();
                            }
                        }
                    }
                }
            }
        }
        log.error("RedisLock 分布式锁获取失败");
        throw new RuntimeException();
    }

}
