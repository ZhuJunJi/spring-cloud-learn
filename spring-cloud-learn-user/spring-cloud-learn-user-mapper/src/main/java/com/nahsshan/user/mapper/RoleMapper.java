package com.nahsshan.user.mapper;

import com.nahsshan.user.common.entity.SysRole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @date 2020/4/19
 * @author J.zhu
 */
public interface RoleMapper {

    /**
     * 用户角色列表
     * @param userId
     * @return
     */
    @Select("Select * from sys_role Where user_id = #{userId}")
    List<SysRole> findByUserId(@Param("userId") Long userId);
}
