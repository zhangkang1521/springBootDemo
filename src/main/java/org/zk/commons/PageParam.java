package org.zk.commons;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 前台分页请求参数
 * Created by zhangkang on 2017/6/28.
 */
public class PageParam<T> {

    private int pageNo; // 页码 start from 1
    private int pageSize; // 分页大小
    private List<Order> orders; // 排序
    private T param; // 实体查询参数

    /**
     * 默认分页第一页
     */
    public final static int DEFAULT_PAGE_NO = 1;
    /**
     * 默认分页大小10
     */
    public final static int DEFAULT_PAGE_SIZE = 10;


    public T getParam() {
        return param;
    }

    public void setParam(T param) {
        this.param = param;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        if(pageNo <= 0) {
            this.pageNo = DEFAULT_PAGE_NO;
        } else {
            this.pageNo = pageNo;
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if(pageSize <= 0) {
            this.pageSize = DEFAULT_PAGE_SIZE;
        } else {
            this.pageSize = pageSize;
        }
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    /**
     * 返回spring-data的Pageable对象
     * @return
     */
    public Pageable getPageable(){
        Sort sort = null;
        if(!CollectionUtils.isEmpty(orders)) {
            List<Sort.Order> sortOrderList = new ArrayList<Sort.Order>(orders.size());
            for(Order order : orders) {
                Sort.Order sortOrder = new Sort.Order(order.getDirection(), order.getProperty());
                sortOrderList.add(sortOrder);
            }
            sort = new Sort(sortOrderList);
        }
        return new PageRequest(pageNo - 1, pageSize, sort);
    }

    /**
     * 排序
     */
    public static class Order {
        String property; // 属性
        Sort.Direction direction; // 升序或降序


        public Sort.Direction getDirection() {
            return direction;
        }

        public void setDirection(Sort.Direction direction) {
            this.direction = direction;
        }

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
        }
    }
}
