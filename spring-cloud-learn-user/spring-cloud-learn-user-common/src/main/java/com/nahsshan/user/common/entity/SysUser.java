package com.nahsshan.user.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author J.zhu
 * @date 2019/7/11
 */
@Data
public class SysUser implements Serializable {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 邮件
     */
    private String email;

    /**
     * 状态（0：锁定，1：解锁）
     */
    private Integer status = 0;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

}
