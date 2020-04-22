package com.nahsshan.user.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author J.zhu
 */
@Data
public class SysUserRole implements Serializable {

    private Long userRoleId;

    private Long userId;

    private Long roleId;

}
