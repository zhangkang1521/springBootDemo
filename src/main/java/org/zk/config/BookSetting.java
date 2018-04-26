package org.zk.config;

import org.springframework.beans.factory.parsing.Location;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 单独文件配置
 * Created by Administrator on 4/26/2018.
 */
@Component
@ConfigurationProperties
@PropertySource("classpath:book.yml")
public class BookSetting {
    private String name;
    private String isbn;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
