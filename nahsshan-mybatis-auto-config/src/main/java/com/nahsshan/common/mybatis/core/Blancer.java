package com.nahsshan.common.mybatis.core;

import com.nahsshan.common.mybatis.pojo.Instance;
import com.nahsshan.common.mybatis.utils.Chooser;
import com.nahsshan.common.mybatis.utils.Pair;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuanyin
 */
@Slf4j
public class Blancer {

    /**
     * Return one instance from the instance list by random-weight.
     *
     * @param instances The list of the instance.
     * @return The random-weight result of the instance
     */
    protected static Instance getInstanceByRandomWeight(List<Instance> instances) {
        log.debug("entry randomWithWeight");
        if (instances == null || instances.size() == 0) {
            log.debug("instances == null || instances.size() == 0");
            return null;
        }

        Chooser<String, Instance> vipChooser = new Chooser<String, Instance>("www.nahsshan.com");

        log.debug("new Chooser");

        List<Pair<Instance>> hostsWithWeight = new ArrayList<Pair<Instance>>();
        for (Instance host : instances) {
            if (host.isHealthy()) {
                hostsWithWeight.add(new Pair<Instance>(host, host.getWeight()));
            }
        }
        log.debug("for (Instance instance : instances)");
        vipChooser.refresh(hostsWithWeight);
        log.debug("vipChooser.refresh");
        return vipChooser.randomWithWeight();
    }
}
