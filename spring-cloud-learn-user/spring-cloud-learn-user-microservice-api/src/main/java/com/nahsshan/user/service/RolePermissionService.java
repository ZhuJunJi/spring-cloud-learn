package com.nahsshan.user.service;

import com.nahsshan.user.common.entity.SysRolePermission;

import java.util.List;

/**
 * @author J.zhu
 * @createdate
 */
public interface RolePermissionService {
    /**
     * 角色权限列表
     *
     * @param roleIds
     * @return
     */
    List<SysRolePermission> findByRoleIds(List<Long> roleIds);
}
