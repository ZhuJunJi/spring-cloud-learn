package com.nahsshan.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author J.zhu
 */
@Slf4j
@Component
public class FeedbackTimeoutNotifyJob extends QuartzJobBean {

    @Override
    protected void executeInternal(@Nullable JobExecutionContext jobExecutionContext) {

        log.info("##################################################################");
        log.info("#############       {}              #############",new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(new Date()) );
        log.info("#############       FeedbackTimeoutNotifyJob         #############");
        log.info("##################################################################");
    }
}
