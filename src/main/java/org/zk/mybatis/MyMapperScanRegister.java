package org.zk.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

public class MyMapperScanRegister implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        AnnotationAttributes annoAttrs = AnnotationAttributes.fromMap(annotationMetadata.getAnnotationAttributes(MyMapperScan.class.getName()));
        MyClassPathScanner myClassPathScanner = new MyClassPathScanner(beanDefinitionRegistry);
        myClassPathScanner.setSqlSessionFactory(null);
        myClassPathScanner.doScan(annoAttrs.getStringArray("value"));
    }
}
