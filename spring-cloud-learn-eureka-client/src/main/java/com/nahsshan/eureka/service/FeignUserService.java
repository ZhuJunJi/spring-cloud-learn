package com.nahsshan.eureka.service;

import com.nahsshan.user.service.UserService;
import com.nahsshan.user.service.UserServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="${user.service.name}",fallback = UserServiceFallback.class)
public interface FeignUserService extends UserService {

}
