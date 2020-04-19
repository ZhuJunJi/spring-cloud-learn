package com.nahsshan.user.service;

import com.nahsshan.user.common.entity.SysPermission;

import java.util.List;

/**
 * 权限Service
 *
 * @author Administrator
 */
public interface PermissionService {
    /**
     * 获取用户权限列表
     *
     * @param userId
     * @return
     */
    List<SysPermission> findByUserId(Long userId);
}
