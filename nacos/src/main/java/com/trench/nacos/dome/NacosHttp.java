package com.trench.nacos.dome;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@RefreshScope
public class NacosHttp {
    @Value("${http}")
    private String http;
    @Value("${requestMethod}")
    private String requestMethod;
    @Value("${filePath}")
    private String file;

    public String getHttp() {
        return http;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public String getFile() {
        return file;
    }
}
