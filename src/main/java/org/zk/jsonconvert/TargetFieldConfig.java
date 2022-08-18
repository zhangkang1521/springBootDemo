package org.zk.jsonconvert;

import lombok.Data;

import java.util.List;

/**
 * @author zhangkang
 * @date 2022/8/18 11:29
 */
@Data
public class TargetFieldConfig {

    private String field;

    /**
     * 表达式，为空则该字段是对象
     */
    private String express;

    /**
     * 嵌套字段
     */
    private List<TargetFieldConfig> targetFieldConfigs;

    public TargetFieldConfig(String field, String express) {
        this.field = field;
        this.express = express;
    }

}
