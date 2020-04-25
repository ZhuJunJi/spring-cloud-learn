package com.nahsshan.common.tomcat;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Connector;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 平滑关闭应用
 * @author J.zhu
 * @date 2019/7/25
 */
@Slf4j
public class CustomShutdown implements TomcatConnectorCustomizer, ApplicationListener<ContextClosedEvent> {

    private static final int TIMEOUT = 30;

    private volatile Connector connector;

    @Override
    public void customize(Connector connector) {
        this.connector = connector;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        // 暂停接受外部的所有新请求
        this.connector.pause();
        // 获取 Connector 对应的线程池
        Executor executor = this.connector.getProtocolHandler().getExecutor();
        if (executor instanceof ThreadPoolExecutor){
            try {
                log.warn("WEB 应用准备关闭");
                ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor)executor;
                threadPoolExecutor.shutdown();
                if(!threadPoolExecutor.awaitTermination(TIMEOUT, TimeUnit.SECONDS)){
                    log.warn("WEB 应用等待关闭超过最大时长 {} 秒，将进行强制关闭！",TIMEOUT);
                    threadPoolExecutor.shutdownNow();
                    if(!threadPoolExecutor.awaitTermination(TIMEOUT,TimeUnit.SECONDS)){
                        log.error("WEB 应用关闭失败!");
                    }
                }
            }catch (InterruptedException ex){
                Thread.currentThread().interrupt();
            }
        }
    }
}
