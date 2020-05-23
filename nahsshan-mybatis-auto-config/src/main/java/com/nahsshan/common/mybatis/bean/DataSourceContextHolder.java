package com.nahsshan.common.mybatis.bean;

import com.nahsshan.common.mybatis.core.SlaveDataSourceBalancer;
import com.nahsshan.common.mybatis.enums.DBTypeEnum;
import com.nahsshan.common.mybatis.pojo.Instance;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author J.zhu
 * @date 2019/6/3
 */
@Slf4j
public class DataSourceContextHolder {

    private static final ThreadLocal<DBTypeEnum> CONTEXT_HOLDER = new ThreadLocal<>();

    public static void set(DBTypeEnum dbType) {
        CONTEXT_HOLDER.set(dbType);

    }

    public static DBTypeEnum get() {
        return CONTEXT_HOLDER.get();
    }

    public static void removeContextHolder(){
        CONTEXT_HOLDER.remove();
    }

    public static void master() {
        set(DBTypeEnum.MASTER);
        log.info("切换到master");
    }

    public static void slave() {
        // 随机权重slave数据源
        Instance dataSourceInstance = SlaveDataSourceBalancer.RandomByWeight.selectInstance();

        set(DBTypeEnum.valueOf(dataSourceInstance.getSourceName()));
    }
}
