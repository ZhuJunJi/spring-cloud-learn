package com.nahsshan.user.service.impl;

import com.nahsshan.user.common.entity.User;
import com.nahsshan.user.mapper.UserMapper;
import com.nahsshan.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 *
 * @author J.zhu
 * @date 2019/7/11
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Integer saveUser(@RequestBody User user) {
        return userMapper.insert(user);
    }

    @Override
    public User getByUserId(@RequestParam("userId") Long userId) {
        return userMapper.getByUserId(userId);
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }
}
