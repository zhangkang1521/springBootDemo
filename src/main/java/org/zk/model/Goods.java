package org.zk.model;

import lombok.Data;

/**
 * 物品类
 *
 * @author zhangkang
 * @date 2022/5/28 17:25
 */
@Data
public class Goods {

    private Long goodsId;

    private Integer goodsType;

    private String goodsName;
}
