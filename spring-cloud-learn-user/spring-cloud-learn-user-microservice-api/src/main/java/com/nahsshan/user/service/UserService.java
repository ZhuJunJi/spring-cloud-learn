package com.nahsshan.user.service;

import com.nahsshan.user.common.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 *
 * @author J.zhu
 * @date 2019/7/11
 */
@FeignClient(name="${user.service.name}",fallback = UserServiceFallback.class)
public interface UserService {
    /**
     * 保存用户
     * @param user
     * @
     */
    @PostMapping("/user/save")
    Integer saveUser(@RequestBody User user);
    /**
     * 查询用户信息
     * @param userId
     * @return
     */
    @GetMapping("/user/get/{userId}")
    User getByUserId(@PathVariable("userId") Long userId);
    /**
     * 查询所有的用户列表
     */
    @GetMapping("/user/findAll")
    List<User> findAll();

}
