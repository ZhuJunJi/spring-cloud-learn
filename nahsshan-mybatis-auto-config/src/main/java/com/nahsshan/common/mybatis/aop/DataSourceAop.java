package com.nahsshan.common.mybatis.aop;

import com.nahsshan.common.mybatis.annotation.Master;
import com.nahsshan.common.mybatis.annotation.Slave;
import com.nahsshan.common.mybatis.bean.DataSourceContextHolder;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 *
 * @author J.zhu
 * @date 2019/6/3
 * 数据源AOP
 * {@link Master Master数据源}
 * {@link Slave Slaver数据源}
 */
@Aspect
@Component
public class DataSourceAop {

    @Pointcut("@annotation(com.nahsshan.common.mybatis.annotation.Slave)")
    public void readPointcut() {

    }

    @Pointcut("@annotation(com.nahsshan.common.mybatis.annotation.Master)")
    public void writePointcut() {

    }

    @Before("readPointcut()")
    public void read() {
        DataSourceContextHolder.slave();
    }

    @Before("writePointcut()")
    public void write() {
        DataSourceContextHolder.master();
    }

    @After("readPointcut(),writePointcut()")
    public void removeContextHolder() {
        DataSourceContextHolder.removeContextHolder();
    }
}
