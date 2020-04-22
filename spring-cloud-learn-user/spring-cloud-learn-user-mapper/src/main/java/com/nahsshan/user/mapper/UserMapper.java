package com.nahsshan.user.mapper;

import com.nahsshan.user.common.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author J.zhu
 * @date 2019/7/11
 */
@Repository
public interface UserMapper {
    /**
     * 保存用户
     * @param user
     * @return
     */
    Integer insert(SysUser user);
    /**
     * 查询用户信息
     * @param userId
     * @return
     */
    SysUser getById(@Param("userId") Long userId);
    /**
     * 查询所有的用户列表
     * @return
     */
    List<SysUser> findList();

    /**
     * 用户帐号查询用户信息
     * @param username
     * @return
     */
    @Select("Select * from sys_user Where username = #{username}")
    SysUser getByUsername(@Param("username") String username);


}
