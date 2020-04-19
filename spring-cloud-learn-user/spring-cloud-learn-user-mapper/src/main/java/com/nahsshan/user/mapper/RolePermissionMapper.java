package com.nahsshan.user.mapper;

import com.nahsshan.user.common.entity.SysRolePermission;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @date 2020/4/19
 * @author J.zhu
 */
@Repository
public interface RolePermissionMapper {


    /**
     * 获取角色权限列表
     * @param roleIds
     * @return
     */
    @Select(value = {
            "<script>",
                    "SELECT * FROM sys_role_permission" +
                    "WHERE role_id IN",
                    "<foreach collection='roleIds' item='roleId' open='(' separator=',' close=')'>",
                    "#{roleId}",
                    "</foreach>",
            "</script>"
    })
    List<SysRolePermission> findByRoleIds(@Param("roleIds") List<Long> roleIds);
}
