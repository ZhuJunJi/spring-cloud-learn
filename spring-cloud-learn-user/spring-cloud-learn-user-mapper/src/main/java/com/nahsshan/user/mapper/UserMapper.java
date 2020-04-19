package com.nahsshan.user.mapper;

import com.nahsshan.user.common.entity.User;
import org.apache.ibatis.annotations.Param;
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
     */
    Integer insert(User user);
    /**
     * 查询用户信息
     * @param userId
     * @return
     */
    User getById(@Param("userId") Long userId);
    /**
     * 查询所有的用户列表
     *
     * 
     */
    List<User> findList();


}
