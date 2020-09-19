package org.zk;

import com.lvmama.order.api.base.vo.RequestBody;
import com.lvmama.order.api.base.vo.ResponseBody;
import com.lvmama.order.service.api.hotel.IApiHotelModifySettmentPriceService;
import com.lvmama.order.vo.hotel.OrdHotelModifySettmentPriceVo;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.rpc.service.GenericService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SupplierTest {
	@Reference
	IApiHotelModifySettmentPriceService hotelModifySettmentPriceService;
	@Reference(generic = true, interfaceName = "com.joyuai.ticket.api.order.service.IOrderService")
	GenericService iOrderService;
	@Reference(generic = true, interfaceName = "com.joyuai.vacation.api.order.service.IOrderApiService", url = "dubbo://10.200.2.137:20886", registry = "N/A")
	GenericService orderApiService;


	@Test
	public void hotel(){
		RequestBody<OrdHotelModifySettmentPriceVo> requestBody = new RequestBody<>();
		OrdHotelModifySettmentPriceVo ordHotelModifySettmentPriceVo = new OrdHotelModifySettmentPriceVo();
		ordHotelModifySettmentPriceVo.setOrderItemId(20200340202L);
		ordHotelModifySettmentPriceVo.setTotalSettlementPrice(1L);
		requestBody.setT(ordHotelModifySettmentPriceVo);
		ResponseBody<Integer> responseBody =  hotelModifySettmentPriceService.updateOrdItemSettmentPrice(requestBody);
	}

	@Test
	public void ticket() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("class", "com.joyuai.ticket.api.order.vo.SubOrderVo");
		params.put("subOrderId", 100);
		params.put("settlementPrice", 1L);
		Object result = iOrderService.$invoke("updateSubOrder", new String[] {"com.joyuai.ticket.api.order.vo.SubOrderVo"}, new Object[]{params});
	}

	@Test
	public void vocation() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("class", "com.joyuai.vacation.api.order.vo.SubOrderVo");
		params.put("subOrderId", 20L);
		params.put("settlementPrice", 1L);
		Object result = orderApiService.$invoke("updateSubOrder", new String[] {"com.joyuai.vacation.api.order.vo.SubOrderVo"}, new Object[]{params});
	}


}
