package com.nahsshan.eureka.service;

import com.nahsshan.common.response.Result;
import com.nahsshan.eureka.service.fallback.FeignUserServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Administrator
 */
@FeignClient(name="${user.service.name}",fallback = FeignUserServiceFallback.class)
public interface FeignUserService {

    @GetMapping(value = "/user/get/{userId}")
    Result get(@PathVariable("userId") Long userId);

}
