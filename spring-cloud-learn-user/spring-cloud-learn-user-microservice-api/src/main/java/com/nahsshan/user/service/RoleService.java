package com.nahsshan.user.service;

import com.nahsshan.user.common.entity.SysRole;

import java.util.List;

public interface RoleService {
    /**
     * 获取用户角色列表
     * @param userId
     * @return
     */
    List<SysRole> findByUserId(Long userId);
}
