package org.zk.mybatis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

@ConfigurationProperties(prefix = "mybatis")
public class MybatisProperties {
    private Resource[] mapperLocations;
    private String typeAliasesPackage;

    public void setTypeAliasesPackage(String typeAliasesPackage) {
        this.typeAliasesPackage = typeAliasesPackage;
    }

    public void setMapperLocations(Resource[] mapperLocations) {
        this.mapperLocations = mapperLocations;
    }

    public Resource[] getMapperLocations() {
        return mapperLocations;
    }

    public String getTypeAliasesPackage() {
        return typeAliasesPackage;
    }
}
