package com.nahsshan.user.service.impl;

import com.nahsshan.user.common.entity.SysRolePermission;
import com.nahsshan.user.mapper.RolePermissionMapper;
import com.nahsshan.user.service.RolePermissionService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author J.zhu
 * @date 2020/4/19
 */
@Service(protocol = "dubbo", version = "1.0.0")
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public List<SysRolePermission> findByRoleIds(List<Long> roleIds) {
        return rolePermissionMapper.findByRoleIds(roleIds);
    }
}
