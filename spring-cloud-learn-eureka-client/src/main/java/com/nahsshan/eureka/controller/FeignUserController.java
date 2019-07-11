package com.nahsshan.eureka.controller;

import com.nahsshan.common.response.Result;
import com.nahsshan.user.common.entity.User;
import com.nahsshan.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author J.zhu
 * @date 2019/7/11
 */
@RestController
@Slf4j
public class FeignUserController implements UserService {

    @Autowired
    UserService userService;

    @Value("${server.port}")
    private Integer port;

    @Override
    public Integer saveUser(User user) {
        return userService.saveUser(user);
    }

    @Override
    public User getByUserId(Long userId) {
        return userService.getByUserId(userId);
    }

    @Override
    public List<User> findAll() {
        return userService.findAll();
    }

}
