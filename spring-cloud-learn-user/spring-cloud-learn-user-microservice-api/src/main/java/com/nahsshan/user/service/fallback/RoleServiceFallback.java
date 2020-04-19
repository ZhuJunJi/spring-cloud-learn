package com.nahsshan.user.service.fallback;

import com.nahsshan.user.common.entity.SysRole;
import com.nahsshan.user.service.RoleService;

import java.util.Collections;
import java.util.List;

/**
 * @author J.zhu
 * @date 2020/4/19
 */
public class RoleServiceFallback implements RoleService {
    @Override
    public List<SysRole> findByUserId(Long userId) {
        return Collections.emptyList();
    }
}
