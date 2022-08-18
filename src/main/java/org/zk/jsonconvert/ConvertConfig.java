package org.zk.jsonconvert;

import lombok.Data;

import java.util.List;

/**
 * @author zhangkang
 * @date 2022/8/18 11:12
 */
@Data
public class ConvertConfig {

    private List<TargetFieldConfig> targetFieldConfigs;
}
