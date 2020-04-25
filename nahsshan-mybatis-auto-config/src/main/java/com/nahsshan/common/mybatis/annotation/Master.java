package com.nahsshan.common.mybatis.annotation;

import java.lang.annotation.*;

/**
 *
 * @author J.zhu
 * @date 2019/6/3
 * 数据库源注解 Master 数据源
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Master {
}
