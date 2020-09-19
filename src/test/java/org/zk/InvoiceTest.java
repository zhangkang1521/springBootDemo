package org.zk;

import com.joyuai.finance.invoice.api.IInvoiceRemoteService;
import com.joyuai.finance.invoice.api.consts.PurchaseWay;
import com.joyuai.finance.invoice.api.param.ApplyInvoiceParam;
import com.joyuai.finance.invoice.api.vo.Result;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InvoiceTest {

	@Reference
	IInvoiceRemoteService invoiceRemoteService;

	@Test
	public void test3() {
		ApplyInvoiceParam applyInvoiceParam = new ApplyInvoiceParam();
		applyInvoiceParam.setOrderId(1000140789L);
		applyInvoiceParam.setPurchaseWay(PurchaseWay.company);
		applyInvoiceParam.setTitle("上海驴途信息科技有限公司");
		applyInvoiceParam.setTaxNumber("91310114055880631E");
		applyInvoiceParam.setContent("旅游费");
		applyInvoiceParam.setReceiveEmail("zhangkang@lvmama.com");
		Result result = invoiceRemoteService.applyInvoice(applyInvoiceParam);
		System.out.println(result);
	}

	@Test
	public void test4() {
		Result result = invoiceRemoteService.getInvoiceByOrderId(10001407891L);
		System.out.println(result);
	}

	@Test
	public void test5() {
		//List<String> contents = invoiceRemoteService.getInvoiceContentsByCategoryId(1L);
		// System.out.println(contents);
	}
}
