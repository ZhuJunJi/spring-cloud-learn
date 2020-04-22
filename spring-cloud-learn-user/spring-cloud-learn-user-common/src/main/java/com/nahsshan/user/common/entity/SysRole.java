package com.nahsshan.user.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author J.zhu
 */
@Data
public class SysRole implements Serializable {

    private Long roleId;

    private String roleName;

    private String roleCode;

    private String roleDescription;


    private String createUser;


    private Date createTime;


    private String updateUser;

    private Date updateTime;
}
