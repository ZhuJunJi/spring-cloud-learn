package com.nahsshan.user.service.impl;


import com.nahsshan.user.common.entity.SysPermission;
import com.nahsshan.user.mapper.PermissionMapper;
import com.nahsshan.user.service.PermissionService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author J.zhu
 */
@Service(protocol = "dubbo", version = "1.0.0")
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<SysPermission> findByUserId(Long userId) {
        return permissionMapper.findByUserId(userId);
    }
}
