package com.nahsshan.eureka.controller;

import com.nahsshan.eureka.feign.FeignUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author J.zhu
 * @date 2019/7/11
 */
@RestController
public class EurekaProviderController {

    @Autowired
    FeignUserService feignUserService;

    @Value("${server.port}")
    private Integer port;

    @GetMapping("/hello")
    public String hello(){
        feignUserService.save();
        return String.format("Hello Spring Cloud Eureka Provider! Port: %s",port);
    }
}
