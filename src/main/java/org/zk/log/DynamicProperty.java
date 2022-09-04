/**
 * <p>Title: DynamicProperty.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: www.zto.com</p>
 */
package org.zk.log;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.spi.PropertyDefiner;
import ch.qos.logback.core.status.Status;

/**
 * 动态配置日志文件路径
 */
public class DynamicProperty implements PropertyDefiner {

    @Override
    public String getPropertyValue() {
        // TODO 获取应用名
        return "/data/logs/spring-boot-demo";
    }

    @Override
    public void setContext(Context context) {

    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void addStatus(Status status) {

    }

    @Override
    public void addInfo(String s) {

    }

    @Override
    public void addInfo(String s, Throwable throwable) {

    }

    @Override
    public void addWarn(String s) {

    }

    @Override
    public void addWarn(String s, Throwable throwable) {

    }

    @Override
    public void addError(String s) {

    }

    @Override
    public void addError(String s, Throwable throwable) {

    }
}
