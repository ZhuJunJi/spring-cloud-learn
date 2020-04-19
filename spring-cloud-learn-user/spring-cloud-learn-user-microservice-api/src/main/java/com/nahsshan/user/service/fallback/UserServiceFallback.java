package com.nahsshan.user.service.fallback;

import com.nahsshan.user.common.entity.SysUser;
import com.nahsshan.user.service.UserService;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author J.zhu
 * @date 2019/7/11
 */
public class UserServiceFallback implements UserService {

    @Override
    public boolean save(SysUser user) {
        return false;
    }

    @Override
    public SysUser getById(Long userId) {
        return new SysUser();
    }

    @Override
    public List<SysUser> findList() {
        return Collections.emptyList();
    }

    @Override
    public SysUser getByUsername(String username) {
        return new SysUser();
    }
}
