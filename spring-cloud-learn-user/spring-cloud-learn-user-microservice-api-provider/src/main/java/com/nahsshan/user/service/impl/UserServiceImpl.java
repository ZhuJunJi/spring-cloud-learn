package com.nahsshan.user.service.impl;

import com.nahsshan.common.mybatis.annotation.Master;
import com.nahsshan.common.mybatis.annotation.Slave;
import com.nahsshan.user.common.entity.SysUser;
import com.nahsshan.user.mapper.UserMapper;
import com.nahsshan.user.service.UserService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 *
 * @author J.zhu
 * @date 2019/7/11
 */
@Service(protocol = "dubbo", version = "1.0.0")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Master
    public boolean save(SysUser user) {
        return userMapper.insert(user) < 1;
    }

    @Override
    @Slave
    public SysUser getById(Long userId) {
        return userMapper.getById(userId);
    }

    @Override
    @Slave
    public List<SysUser> findList() {
        return userMapper.findList();
    }

    @Override
    public SysUser getByUsername(String username) {
        return userMapper.getByUsername(username);
    }
}
