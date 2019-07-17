package com.nahsshan.eureka.service;

import com.nahsshan.user.service.UserService;
import com.nahsshan.user.service.UserServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 */
@FeignClient(name="${user.service.name}",fallback = UserServiceFallback.class)
@Service
public interface FeignUserService extends UserService {

}
