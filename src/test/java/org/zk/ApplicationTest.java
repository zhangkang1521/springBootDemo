package org.zk;

import com.joyuai.finance.distributor.api.IBillService;
import com.joyuai.finance.distributor.api.IReconRuleService;
import com.joyuai.finance.distributor.api.IReconService;
import com.joyuai.finance.distributor.api.vo.Result;
import com.joyuai.finance.invoice.api.IInvoiceRemoteService;
import com.joyuai.finance.invoice.api.consts.PurchaseWay;
import com.joyuai.finance.invoice.api.param.ApplyInvoiceParam;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Administrator on 8/5/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Reference
    IBillService billService;
    @Reference
    IReconService iReconService;


    @Test
    public void test1() throws Exception {
        Result<byte[]> result =  billService.exportBillItems(10301L);
        try (FileOutputStream fileOutputStream = new FileOutputStream(new File("E:/bill.xlsx"))){
            fileOutputStream.write(result.getData());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
       Result result = iReconService.findBillReconRuleVo(10129L);
        System.out.println(result);
    }


}
