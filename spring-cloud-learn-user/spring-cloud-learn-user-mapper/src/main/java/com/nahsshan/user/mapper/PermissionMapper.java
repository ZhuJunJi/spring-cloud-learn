package com.nahsshan.user.mapper;

import com.nahsshan.user.common.entity.SysPermission;
import com.nahsshan.user.common.entity.SysRolePermission;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 权限Mapper
 * @date 2020/4/19
 * @author J.zhu
 */
@Repository
public interface PermissionMapper {
    /**
     * 获取用户权限列表
     * @param userId
     * @return
     */
    @Select("SELECT p.* FROM sys_permission p LEFT JOIN sys_role_permission rp on p.permission_id = rp.permission_id LEFT JOIN sys_user_role ur on rp.role_id = ur.role_id AND ur.user_id = #{userId}")
    List<SysPermission> findByUserId(@Param("userId") Long userId);

    /**
     * 获取角色权限列表
     * @param roleIds
     * @return
     */
    @Select(value = {
            "<script>",
                "SELECT p.* FROM sys_permission p " +
                "LEFT JOIN sys_role_permission rp on p.permission_id = rp.permission_id",
                "WHERE rp.role_id IN",
                    "<foreach collection='roleIds' item='roleId' open='(' separator=',' close=')'>",
                        "#{roleId}",
                    "</foreach>",
            "</script>"
    })
    List<SysPermission> findByRoleIds(@Param("roleIds") List<Long> roleIds);
}
