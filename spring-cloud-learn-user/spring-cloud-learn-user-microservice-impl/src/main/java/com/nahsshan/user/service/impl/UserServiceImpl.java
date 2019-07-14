package com.nahsshan.user.service.impl;

import com.nahsshan.common.db.annotation.Master;
import com.nahsshan.common.db.annotation.Slave;
import com.nahsshan.user.common.entity.User;
import com.nahsshan.user.mapper.UserMapper;
import com.nahsshan.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author J.zhu
 * @date 2019/7/11
 */
@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Master
    public Integer saveUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    @Slave
    public User getById(Long userId) {
        return userMapper.getByUserId(userId);
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }
}
