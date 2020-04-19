package com.nahsshan.eureka.service;

import com.nahsshan.common.response.Result;
import com.nahsshan.eureka.service.fallback.FeignUserServiceFallback;
import com.nahsshan.user.common.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Administrator
 */
@Service
@FeignClient(name="${user.service.name}",fallback = FeignUserServiceFallback.class)
public interface FeignUserService {

    /**
     * ID查询用户信息
     * @param userId
     * @return
     */
    @GetMapping(value = "/user/get/{userId}")
    Result<User> get(@PathVariable("userId") Long userId);

}
