package org.zk.eazyexcel;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;

public class ReadTest {

    @Test
    public void simpleRead() {
        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        // 写法1：
//        String fileName = TestFileUtil.getPath() + "demo" + File.separator + "demo.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
//        EasyExcel.read("E:/a.xlsx", DemoData.class, new DemoDataListener()).sheet().doRead();
        EasyExcel.read("E:/a.xlsx", new DemoDataListener()).sheet().headRowNumber(1).doRead();

    }


}
