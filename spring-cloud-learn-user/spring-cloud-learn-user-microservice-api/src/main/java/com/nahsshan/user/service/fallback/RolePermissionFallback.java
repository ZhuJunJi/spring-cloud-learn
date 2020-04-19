package com.nahsshan.user.service.fallback;

import com.nahsshan.user.common.entity.SysRolePermission;
import com.nahsshan.user.service.RolePermissionService;

import java.util.Collections;
import java.util.List;
/**
 * @date 2020/4/19
 * @author J.zhu
 */
public class RolePermissionFallback implements RolePermissionService {
    @Override
    public List<SysRolePermission> findByRoleIds(List<Long> roleIds) {
        return Collections.emptyList();
    }
}
