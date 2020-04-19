package com.nahsshan.user.mapper;

import com.nahsshan.user.common.entity.SysPermission;
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
    @Select("Select * from sys_permission Where user_id = #{userId")
    List<SysPermission> findByUserId(@Param("userId") Long userId);
}
