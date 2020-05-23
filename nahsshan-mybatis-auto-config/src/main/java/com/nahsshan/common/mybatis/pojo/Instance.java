package com.nahsshan.common.mybatis.pojo;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Instance
 *
 * @author zhujunji
 */
@Data
public class Instance {

    public Instance(){}

    public Instance(String sourceName, double weight){
        this.sourceName = sourceName;
        this.weight = weight;
    }

    /**
     * instance weight
     */
    private double weight = 1.0D;

    /**
     * instance health status
     */
    private boolean healthy = true;

    /**
     * If instance is enabled to accept request
     */
    private boolean enabled = true;

    /**
     * If instance is ephemeral
     *
     * @since 1.0.0
     */
    private boolean ephemeral = true;

    /**
     * source information of instance
     */
    private String sourceName;

    /**
     * user extended attributes
     */
    private Map<String, String> metadata = new HashMap();

    public void addMetadata(String key, String value) {
        this.metadata.put(key, value);
    }
}
