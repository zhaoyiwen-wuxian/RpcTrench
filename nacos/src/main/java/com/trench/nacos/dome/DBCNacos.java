package com.trench.nacos.dome;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@RefreshScope
public class DBCNacos {
    @Value("${spring.datasource.mysql.driver-class-name}")
    private String driver;

    public String getDriver() {
        return driver;
    }
}
