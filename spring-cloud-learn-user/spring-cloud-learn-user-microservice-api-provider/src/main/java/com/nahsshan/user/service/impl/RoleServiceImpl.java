package com.nahsshan.user.service.impl;

import com.nahsshan.user.common.entity.SysRole;
import com.nahsshan.user.mapper.RoleMapper;
import com.nahsshan.user.service.RoleService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author J.zhu
 * @date 2020/4/19
 */
@Service(protocol = "dubbo", version = "1.0.0")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<SysRole> findByUserId(Long userId) {
        return roleMapper.findByUserId(userId);
    }
}
