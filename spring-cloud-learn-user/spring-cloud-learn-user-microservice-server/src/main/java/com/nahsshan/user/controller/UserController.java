package com.nahsshan.user.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.nahsshan.common.redis.utils.RedisUtil;
import com.nahsshan.common.response.Result;
import com.nahsshan.user.common.entity.User;
import com.nahsshan.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

/**
 *
 * @author J.zhu
 * @date 2019/7/11
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController{
    
    @Value("${server.port}")
    private Integer port;
    @Value("${spring.cloud.client.ip-address}")
    private String ipAddress;

    @Resource(name = "userServiceImpl")
    private UserService userService;

    private final static Random RANDOM = new Random();

    @PostMapping("/save")
    public Result<Integer> saveUser(@RequestBody User user) {
        Integer i = userService.saveUser(user);
        return Result.newSuccessResult(i);
    }

    @GetMapping("/get/{userId}")
    @SentinelResource(value="/user/get",blockHandler="getById",blockHandlerClass=UserControllerBlock.class)
    public Result<User> getById(@PathVariable("userId") Long userId){
        User user = userService.getById(userId);
        RedisUtil.set("user",user);
        return Result.newSuccessResult(user);
    }

    /**
     * 获取所有用户列表
     * @return
     */
    @GetMapping("/findAll")
    public Result<List<User>> findAll() {
        // 通过休眠来模拟执行时间
        log.info("{}:{} UserController method;{}",ipAddress,port,"getUsers");
        long executeTime = RANDOM.nextInt(200);
        System.out.println("Execute Time : " + executeTime + " ms");
        List<User> userList = userService.findAll();
        return Result.newSuccessResult(userList);
    }
}
