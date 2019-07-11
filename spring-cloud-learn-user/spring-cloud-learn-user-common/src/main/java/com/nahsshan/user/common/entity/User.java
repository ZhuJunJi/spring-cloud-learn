package com.nahsshan.user.common.entity;

import lombok.Data;

/**
 *
 * @author J.zhu
 * @date 2019/7/11
 */
@Data
public class User {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户年龄
     */
    private Integer age;

}
