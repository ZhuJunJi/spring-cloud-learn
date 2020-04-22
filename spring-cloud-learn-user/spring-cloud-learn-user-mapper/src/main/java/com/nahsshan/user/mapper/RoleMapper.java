package com.nahsshan.user.mapper;

import com.nahsshan.user.common.entity.SysRole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @date 2020/4/19
 * @author J.zhu
 */
@Repository
public interface RoleMapper {

    /**
     * 获取角色权限列表
     * @param userId
     * @return
     */
    @Select(value = "SELECT * FROM sys_role WHERE role_id IN (select role_id from sys_user_role where user_id = #{userId})")
    List<SysRole> findByUserId(@Param("userId") Long userId);
}
