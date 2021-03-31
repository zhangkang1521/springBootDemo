package org.zk;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zk.service.UserParam;
import org.zk.service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LockInterceptorTest {

	@Autowired
	UserService userService;

	@Test
	public void test() throws Exception {
		new Thread(new Runnable() {
			@Override
			public void run() {
				UserParam userParam = new UserParam();
				userParam.setOrderIds(new ArrayList<>());
//				userParam.setUserId(10L);
				userParam.setMap(new HashMap<>());
				userService.sayHello(userParam);
			}
		}).start();
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				UserParam userParam = new UserParam();
//				userParam.setOrderIds(Arrays.asList("1"));
//				userParam.setUserId(10L);
//				userService.sayHello(userParam);
//			}
//		}).start();
		System.in.read();

	}
}
