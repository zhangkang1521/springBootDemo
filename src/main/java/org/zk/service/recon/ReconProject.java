package org.zk.service.recon;

import lombok.Data;

/**
 * 对账项目
 * @author zhangkang
 * @date 2022/8/7 18:29
 */
@Data
public class ReconProject {

    /**
     * a方表名
     */
    private String aTableName;

    /**
     * b方表名
     */
    private String bTableName;

    /**
     * a方对比键字段名
     */
    private String aKey;

    /**
     * b方对比键字段名
     */
    private String bKey;

    /**
     * a方对比金额字段名
     */
    private String aAmount;

    /**
     * b方对比金额字段名
     */
    private String bAmount;

}
