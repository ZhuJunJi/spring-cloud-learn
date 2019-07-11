package com.nahsshan.user.service;

import com.nahsshan.user.common.entity.User;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author J.zhu
 * @date 2019/7/11
 */
public class UserServiceFallback implements UserService{

    @Override
    public Integer saveUser(User user) {
        return -1;
    }

    @Override
    public User getByUserId(Long userId) {
        return new User();
    }

    @Override
    public List<User> findAll() {
        return Collections.emptyList();
    }
}
