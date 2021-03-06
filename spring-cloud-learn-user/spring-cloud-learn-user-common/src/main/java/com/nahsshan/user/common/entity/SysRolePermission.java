package com.nahsshan.user.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author J.zhu
 */
@Data
public class SysRolePermission implements Serializable {

    private Long rolePermissionId;

    private Long roleId;

    private Long permissionId;
}
