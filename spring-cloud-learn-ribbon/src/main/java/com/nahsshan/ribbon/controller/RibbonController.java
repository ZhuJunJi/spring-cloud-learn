package com.nahsshan.ribbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author J.zhu
 * @date 2019/7/11
 */
@RestController
public class RibbonController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${service-provider.name}")
    private String providerName;

    @GetMapping("hello")
    public String hello(){
        return restTemplate.getForObject("http://" + providerName + "/hello", String.class);
    }
}
