package org.zk;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;

public class ApolloTest {

	// -Dapollo.meta=http://106.54.227.205:8080 -Dapp.id=finance-test
	public static void main(String[] args) {
		Config config = ConfigService.getAppConfig();
		String someKey = "timeout";
		String value = config.getProperty(someKey, "1");
		System.out.println(value);
	}
}
