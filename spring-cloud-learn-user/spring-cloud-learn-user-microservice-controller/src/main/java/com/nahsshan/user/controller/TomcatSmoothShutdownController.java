package com.nahsshan.user.controller;

import com.nahsshan.common.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 平滑关闭Spring boot内嵌Tomcat 测试
 * @author J.zhu
 * @date 2019/7/25
 */
@RestController()
@RequestMapping("/tomcat")
@Slf4j
public class TomcatSmoothShutdownController {

    @RequestMapping("/working")
    public Result<String> working() throws InterruptedException {
        log.warn("开始处理业务");
        Thread.sleep(2000);
        log.warn("结束处理业务");
        return Result.newSuccessResult("成功");
    }
}
