package com.nahsshan.user.controller;

import com.nahsshan.common.redisson.utils.RedissonLockUtil;
import com.nahsshan.common.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author J.zhu
 * @date 2019/7/11
 */
@RestController
@Slf4j
public class RedisController {

    /**
     * RedissonLock 测试接口
     * @return
     */
    @GetMapping("/redis/lock/{lockKey}")
    public Result redissonLock(@PathVariable("lockKey") String lockKey) {
        boolean res = false;
        try{
            res = RedissonLockUtil.tryLock(lockKey, TimeUnit.SECONDS, 5, 20);
            if(res){
                System.out.println(String.format("获得锁：%s",lockKey));
                Thread.sleep(2000);
            }
        }catch (Throwable e){
            log.error("测试RedissonLock异常：",e);
            throw new RuntimeException();
        }finally {
            if(res){
                log.info("Lock key: {},unlock",lockKey);
                RedissonLockUtil.unlock(lockKey);
            }
        }
        return Result.newSuccessResult();
    }

}
