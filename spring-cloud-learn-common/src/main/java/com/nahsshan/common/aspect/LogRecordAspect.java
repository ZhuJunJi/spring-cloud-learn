package com.nahsshan.common.aspect;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * Created by J.zhu on 2020/4/18.
 *
 * @author Administrator
 */
@SuppressWarnings("all")
@Slf4j
@Component
@Aspect
public class LogRecordAspect {

    @Value("${server.port}")
    private Integer port;
    @Value("${spring.cloud.client.ip-address}")
    private String serverAddress;

    /**
     * 两个..代表所有子目录，最后括号里的两个..代表所有参数
     */
    @Pointcut("execution(* com.nahsshan.*.controller.*Controller.*(..))")
    public void excuteService() {
    }

    /**
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("excuteService()")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        Long startTime = System.currentTimeMillis();
        String requestUuid = UUID.randomUUID().toString().replaceAll("-", "");
        try {
            Object[] args = point.getArgs();
            log.info("请求开始 ServerAddress: {}:{}, RequestId: {}, StartTime: {}, url: {}, method: {}, uri: {}, params: {}", serverAddress, port, requestUuid, startTime, url, method, uri, JSONObject.toJSONString(args));
        } catch (Exception e) {
            log.info("请求开始 ServerAddress: {}:{}, RequestId: {}, StartTime: {}, url: {}, method: {}, uri: {}, params: {}", serverAddress, port, requestUuid, startTime, url, method, uri, queryString);
        }
        // result的值就是被拦截方法的返回值
        Object result = point.proceed();
        Long endTime = System.currentTimeMillis();
        log.info("请求结束 ServerAddress: {}:{}, RequestId: {}, EndTime: {}, url: {}, method: {}, uri: {}, result: {}, 耗时: {}", serverAddress, port, requestUuid, endTime, url, method, uri, JSONObject.toJSONString(result), endTime - startTime);
        return result;
    }
}
