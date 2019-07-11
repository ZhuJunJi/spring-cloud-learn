package com.nahsshan.eureka.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author J.zhu
 * @date 2019/7/11
 */
@FeignClient(value = "${user.service.name}")
public interface FeignUserService {

    @GetMapping("/feign/user/save")
    Integer save();
    

}
