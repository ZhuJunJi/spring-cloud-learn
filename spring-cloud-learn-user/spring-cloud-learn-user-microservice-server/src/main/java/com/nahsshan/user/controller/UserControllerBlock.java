package com.nahsshan.user.controller;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.google.common.collect.Lists;
import com.nahsshan.common.response.Result;
import com.nahsshan.user.common.entity.User;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author J.zhu
 * @date 2019/7/19
 */
@Slf4j
public class UserControllerBlock{

    public static Result saveUser(User user,BlockException ex) {
        return Result.newSuccessResult(0,"服务流量异常！");
    }

    public static Result getById(Long userId, BlockException ex) {
        return Result.newSuccessResult(new User(),"服务流量异常！");
    }

    public static Result findAll(BlockException ex) {
        return Result.newSuccessResult(Lists.newArrayList(),"服务流量异常！");
    }
}
