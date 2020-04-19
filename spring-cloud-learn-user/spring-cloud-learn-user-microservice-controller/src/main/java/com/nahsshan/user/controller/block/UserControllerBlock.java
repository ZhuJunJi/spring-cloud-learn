package com.nahsshan.user.controller.block;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.google.common.collect.Lists;
import com.nahsshan.common.response.Result;
import com.nahsshan.user.common.entity.User;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * UserController限流处理
 * @author J.zhu
 * @date 2019/7/19
 */
@Slf4j
public class UserControllerBlock{

    public static Result<Boolean> save(User user, BlockException ex) {
        return Result.newSuccessResult(false,"服务流量异常！");
    }

    public static Result<User> get(Long userId, BlockException ex) {
        return Result.newSuccessResult(new User(),"服务流量异常！");
    }

    public static Result<List<User>> findList(BlockException ex) {
        return Result.newSuccessResult(Lists.newArrayList(),"服务流量异常！");
    }
}
