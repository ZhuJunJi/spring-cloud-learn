package com.nahsshan.common.mybatis.core;

import com.nahsshan.common.mybatis.enums.DBTypeEnum;
import com.nahsshan.common.mybatis.pojo.Instance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * mybatis slave datasource balancer
 *
 * @author zhujunji
 */
public class SlaveDataSourceBalancer extends Blancer{

    public static class RandomByWeight {

        private static volatile List<Instance> dataSourceInstances;

        private static double slave1Weight;

        private static double slave2Weight;

        @Value("{spring.datasource.slave1.weight}")
        public void setSlave1Weight(double slave1Weight) {
            RandomByWeight.slave1Weight = slave1Weight;
        }

        @Value("{spring.datasource.slave2.weight}")
        public void setSlave2Weight(double slave2Weight) {
            RandomByWeight.slave2Weight = slave2Weight;
        }

        private static void initDataSourceInstances() {
            if(CollectionUtils.isEmpty(dataSourceInstances)){
                synchronized (RandomByWeight.class){
                    if(CollectionUtils.isEmpty(dataSourceInstances)){

                        dataSourceInstances = new ArrayList<>();

                        dataSourceInstances.add(new Instance(DBTypeEnum.SLAVE2.name(),slave1Weight));
                        dataSourceInstances.add(new Instance(DBTypeEnum.SLAVE2.name(),slave2Weight));
                    }
                }
            }
        }

        public static Instance selectInstance() {

            if (CollectionUtils.isEmpty(dataSourceInstances)) {
                initDataSourceInstances();
            }

            return getDataSourceByRandomWeight(dataSourceInstances);
        }
    }


    public static Instance getDataSourceByRandomWeight(List<Instance> instances){
        return getInstanceByRandomWeight(instances);
    }
}
