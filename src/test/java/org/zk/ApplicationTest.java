package org.zk;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zk.dao.GoodsDao;
import org.zk.model.Goods;

import java.util.List;

/**
 * Created by Administrator on 8/5/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Autowired
    private GoodsDao goodsDao;

    @Test
    public void findById() {
        List<Goods> list = goodsDao.findById(1L);
    }

    @Test
    public void findByName() {
        List<Goods> list = goodsDao.findByGoodsName("124");
    }

    @Test
    public void insert() {
        Goods goods = new Goods();
        goods.setGoodsId(22L);
        goods.setGoodsType(1);
        goods.setGoodsName("bbb");
        goodsDao.insert(goods);
    }
}
