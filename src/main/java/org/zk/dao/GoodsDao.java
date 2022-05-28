package org.zk.dao;


import org.springframework.stereotype.Repository;
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

    void insert(Goods goods);
}
