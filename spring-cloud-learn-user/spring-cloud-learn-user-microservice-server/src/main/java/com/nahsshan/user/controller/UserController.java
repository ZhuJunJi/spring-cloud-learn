package com.nahsshan.user.controller;

import com.nahsshan.common.utils.SnowFlake;
import com.nahsshan.user.common.entity.User;
import com.nahsshan.user.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.Random;

/**
 * Created by J.zhu on 2019/7/11.
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    private final static Random random = new Random();

    @GetMapping("/user/save")
    public Integer saveUser(){
        User user=new User();
        user.setUserId(SnowFlake.nextId());
        user.setUserName("张三");
        user.setAge(18);
        return userService.saveUser(user);
    }

    @GetMapping("/user/get/{userId}")
    public User get(@PathVariable Long userId){
        return userService.getByUserId(userId);
    }

    /**
     * 获取所有用户列表
     * @return
     */
    @HystrixCommand(
            // Command 配置,设置操作时间为 100 毫秒
            commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100")},
            // 设置 fallback 方法
            fallbackMethod = "fallbackForGetUsers"
    )
    @GetMapping("/user/list")
    public Collection<User> getUsers() throws InterruptedException {
        // 通过休眠来模拟执行时间
        long executeTime = random.nextInt(200);
        System.out.println("Execute Time : " + executeTime + " ms");
        Thread.sleep(executeTime);
        return userService.findAll();
    }

    /**
     * 超过100毫秒就返回空集合
     * @return
     */
    public Collection<User> fallbackForGetUsers() {
        return Collections.emptyList();
    }
}
