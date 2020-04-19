package com.nahsshan.user.service;

import com.nahsshan.user.common.entity.SysUser;

import java.util.List;

/**
 *
 * @author J.zhu
 * @date 2019/7/11
 */
public interface UserService {
    /**
     * 保存用户
     * @param user
     * @return
     */
    boolean save(SysUser user);
    /**
     * 查询用户信息
     * @param userId
     * @return
     */
    SysUser getById(Long userId);
    /**
     * 查询所有的用户列表
     * @return List<User>
     */
    List<SysUser> findList();

    /**
     * 用户名获取用户
     * @param username
     * @return
     */
    SysUser getByUsername(String username);

}
