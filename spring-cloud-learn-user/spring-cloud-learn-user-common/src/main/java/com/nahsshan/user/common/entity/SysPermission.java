package com.nahsshan.user.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 权限实体类
 * @author J.zhu
 */
@Data
public class SysPermission implements Serializable {

    private Long permissionId;

    private Long pid;

    private Integer type;

    private String name;

    private String code;

    private String uri;

    private Integer seq = 1;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;
}
