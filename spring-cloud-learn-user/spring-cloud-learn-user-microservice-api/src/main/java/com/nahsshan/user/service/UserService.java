package com.nahsshan.user.service;

import com.nahsshan.user.common.entity.User;

import java.util.List;

/**
 *
 * @author J.zhu
 * @date 2019/7/11
 */
public interface UserService {
    /**
     * 保存用户
     * @param user
     * @
     */
    Integer saveUser(User user);
    /**
     * 查询用户信息
     * @param userId
     * @return
     */
    User getById(Long userId);
    /**
     * 查询所有的用户列表
     */
    List<User> findAll() throws InterruptedException;

}
