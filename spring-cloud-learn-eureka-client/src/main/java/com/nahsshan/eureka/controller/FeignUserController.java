package com.nahsshan.eureka.controller;

import com.nahsshan.common.response.Result;
import com.nahsshan.eureka.service.FeignUserService;
import com.nahsshan.user.common.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author J.zhu
 * @date 2019/7/11
 */
@RestController
@Slf4j
public class FeignUserController {

//    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private FeignUserService feignUserService;

    @Value("${server.port}")
    private Integer port;

    @GetMapping("user/get/{userId}")
    private Result getById(@PathVariable("userId") Long userId) {
        User user = feignUserService.getById(userId);
        return Result.newSuccessResult(user);
    }



}
