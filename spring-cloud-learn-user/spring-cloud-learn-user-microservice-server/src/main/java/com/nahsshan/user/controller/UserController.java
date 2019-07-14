package com.nahsshan.user.controller;

import com.nahsshan.user.common.entity.User;
import com.nahsshan.user.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by J.zhu on 2019/7/11.
 */
@RestController
@Slf4j
public class UserController implements UserService{

    @Value("${server.port}")
    private Integer port;
    @Value("${spring.cloud.client.ip-address}")
    private String ipAddress;

    @Resource(name = "userServiceImpl")
    private UserService userService;

    private final static Random random = new Random();

    @Override
    public Integer saveUser(User user) {
        return userService.saveUser(user);
    }

    @Override
    public User getById(@PathVariable Long userId){
        log.info("{}:{} UserController method;{} param:userId: {}",ipAddress,port,"get",userId);
        return userService.getById(userId);
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
    @Override
    public List<User> findAll() throws InterruptedException {
        // 通过休眠来模拟执行时间
        log.info("{}:{} UserController method;{}",ipAddress,port,"getUsers");
        long executeTime = random.nextInt(200);
        System.out.println("Execute Time : " + executeTime + " ms");
        Thread.sleep(executeTime);
        return userService.findAll();
    }

    /**
     * 超过100毫秒就返回空集合
     * @return
     */
    public List<User> fallbackForGetUsers() {
        log.info("{}:{} UserController method;{}",ipAddress,port,"fallbackForGetUsers");
        return Collections.emptyList();
    }

}
