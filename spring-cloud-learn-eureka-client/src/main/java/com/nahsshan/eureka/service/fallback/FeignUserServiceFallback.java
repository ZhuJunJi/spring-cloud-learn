package com.nahsshan.eureka.service.fallback;

import com.nahsshan.common.response.Result;
import com.nahsshan.eureka.service.FeignUserService;
import com.nahsshan.user.common.entity.User;
import org.springframework.stereotype.Component;

/**
 * 用户服务，服务降级
 * @author J.zhu
 * @date 2019/7/18
 */
@Component
public class FeignUserServiceFallback implements FeignUserService {
    @Override
    public Result<User> get(Long userId) {
        return Result.newSuccessResult(null,"服务异常，服务降级！");
    }
}
