package com.trench.nacos.dome;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.io.Serializable;

@RefreshScope
public class Nacos implements Serializable {
    @Value("${hostname}")
    private String hostname;
    @Value("${port}")
    private Integer port;


    public String getHostname() {
        return hostname;
    }

    public Integer getPort() {
        return port;
    }

}
