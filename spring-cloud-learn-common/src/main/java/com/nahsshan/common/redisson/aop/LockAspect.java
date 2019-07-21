package com.nahsshan.common.redisson.aop;

import com.nahsshan.common.redisson.annotation.LockKey;
import com.nahsshan.common.redisson.utils.RedissonLockUtil;
import com.nahsshan.common.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component
@Scope
@Aspect
@Order(1)
@Slf4j
public class LockAspect {
	/**
     * 思考：为什么不用synchronized
     * service 默认是单例的，并发下lock只有一个实例
     * 互斥锁 参数默认false，不公平锁
     */
	private static  Lock lock = new ReentrantLock(true);

    /**
     * Service层切点
     */
	@Pointcut("@annotation(com.nahsshan.common.redisson.annotation.RedisLock)")
	public void redisLockAspect() {

	}

	@Around("redisLockAspect()")
	public  Object redisLockAround(ProceedingJoinPoint joinPoint) {
        Object obj = null;
        String lockKey = getLockKey(joinPoint);
        if(StringUtils.isNotBlank(lockKey)){
            boolean res=false;
            try {
                res = RedissonLockUtil.tryLock(lockKey, TimeUnit.SECONDS, 3, 20);
                if(res){
                    log.info("获取锁 lockKey：{}",lockKey);
                    obj = joinPoint.proceed();
                }else{
                    return Result.newFailureResult();
                }
            } catch (Throwable e) {
                e.printStackTrace();
                throw new RuntimeException();
            } finally{
                if(res){
                    log.info("释放锁 lockKey：{}",lockKey);
                    RedissonLockUtil.unlock(lockKey);
                }
            }
        }
        return obj;
	}

    /**
     * 获取方法上加了{@link com.nahsshan.common.redisson.annotation.LockKey}注解的为lockKey
     * @param joinPoint
     * @return
     */
    private String getLockKey(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (Annotation[] parameterAnnotation: parameterAnnotations) {
            int paramIndex= ArrayUtils.indexOf(parameterAnnotations, parameterAnnotation);
            for (Annotation annotation: parameterAnnotation) {
                if (annotation instanceof LockKey){
                    return args[paramIndex].toString();
                }
            }
        }
        return null;
    }

}
