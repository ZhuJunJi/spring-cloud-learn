package com.nahsshan.user.controller.block;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.google.common.collect.Lists;
import com.nahsshan.common.response.Result;
import com.nahsshan.user.common.entity.SysUser;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * UserController限流处理
 * @author J.zhu
 * @date 2019/7/19
 */
@Slf4j
public class UserControllerBlock{

    public static Result<Boolean> save(SysUser user, BlockException ex) {
        return Result.newSuccessResult(false,"服务流量异常！");
    }

    public static Result<SysUser> get(Long userId, BlockException ex) {
        return Result.newSuccessResult(new SysUser(),"服务流量异常！");
    }

    public static Result<List<SysUser>> findList(BlockException ex) {
        return Result.newSuccessResult(Lists.newArrayList(),"服务流量异常！");
    }
}
