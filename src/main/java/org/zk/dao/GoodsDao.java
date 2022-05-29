package org.zk.dao;


import org.zk.model.Goods;

import java.util.List;

/**
 * TODO
 *
 * @author zhangkang
 * @date 2022/5/28 17:22
 */
public interface GoodsDao {

    List<Goods> findById(Long goodsId);

    List<Goods> findByGoodsName(String goodsName);

    void insert(Goods goods);
}
