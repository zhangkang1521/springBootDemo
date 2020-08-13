package org.zk.config;

import com.baomidou.mybatisplus.extension.toolkit.PackageHelper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;
import org.zk.enums.BaseEnum;
import org.zk.typehandlers.EnumCodeTypeHandler;

import java.util.Set;

@Configuration
public class MybatisPlusConfig implements InitializingBean {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public void afterPropertiesSet() throws Exception {
		registerEnumCodeTypeHandler();
	}

	private void registerEnumCodeTypeHandler() {
		String typeEnumsPackage = "org.zk.enums";
		Set<Class> classes = PackageHelper.scanTypePackage(typeEnumsPackage);
		TypeHandlerRegistry typeHandlerRegistry = sqlSessionFactory.getConfiguration().getTypeHandlerRegistry();
		for (Class cls : classes) {
			if (cls.isEnum()) {
				if (BaseEnum.class.isAssignableFrom(cls)) {
					typeHandlerRegistry.register(cls, EnumCodeTypeHandler.class);
				}
			}
		}
	}
}
