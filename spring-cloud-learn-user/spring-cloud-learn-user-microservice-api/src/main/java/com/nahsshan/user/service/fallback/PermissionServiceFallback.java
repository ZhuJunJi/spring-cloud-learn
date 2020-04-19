package com.nahsshan.user.service.fallback;

import com.nahsshan.user.common.entity.SysPermission;
import com.nahsshan.user.service.PermissionService;

import java.util.Collections;
import java.util.List;

/**
 * @date 2020/4/19
 * @author J.zhu
 */
public class PermissionServiceFallback implements PermissionService {
    @Override
    public List<SysPermission> findByUserId(Long userId) {
        return Collections.emptyList();
    }
}
