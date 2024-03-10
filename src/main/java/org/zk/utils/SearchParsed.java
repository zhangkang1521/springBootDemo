package org.zk.utils;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 将搜索语句解析成集合关系的实体
 */
@Data
public class SearchParsed {

    /**
     * 取交集（内部list取并集）
     */
    private List<List<String>> intersects = new ArrayList<>();

    /**
     * 取差集
     */
    private List<String> difference = new ArrayList<>();


}
