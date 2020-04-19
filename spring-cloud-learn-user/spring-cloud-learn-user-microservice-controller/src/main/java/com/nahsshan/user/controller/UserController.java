package com.nahsshan.user.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.nahsshan.common.response.Result;
import com.nahsshan.user.common.entity.User;
import com.nahsshan.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author J.zhu
 * @date 2019/7/11
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController{

    @Reference(version = "1.0.0",timeout = 10000,retries = 0)
    private UserService userService;

    @PostMapping("/save")
    public Result<Boolean> save(@RequestBody User user) {
        return Result.newSuccessResult(userService.save(user));
    }

    @GetMapping("/get/{userId}")
    @SentinelResource(value="/user/get",blockHandler="get",blockHandlerClass=UserControllerBlock.class)
    public Result<User> get(@PathVariable("userId") Long userId){
        User user = userService.getById(userId);
        return Result.newSuccessResult(user);
    }

    /**
     * 获取所有用户列表
     * @return
     */
    @GetMapping("/findList")
    public Result<List<User>> findList() {
        return Result.newSuccessResult(userService.findList());
    }
}
