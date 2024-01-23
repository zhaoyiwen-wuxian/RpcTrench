package com.trench.lpml.dome;

import com.trench.MapRemoteRegister;
import com.trench.lpml.HelloServiceImpl;
import com.trench.nacos.dome.Nacos;
import com.trench.protocol.HttpServer;
import com.trench.register.LocalRegister;
import com.trench.server.HelloService;

public class Provider {
    public static void main(String[] args) {
        //先注册
        //这里可以设置成为一个自定义注解，然后从注解中进行加载到注册对象中，通过反射
        LocalRegister.register(HelloService.class.getName(), HelloServiceImpl.class, "1.0");
        //注册中心注册
        Nacos nacos = new Nacos();
        MapRemoteRegister.register(HelloService.class.getName(), nacos);
        // tomcat

        HttpServer httpServer = new HttpServer();
        httpServer.start(nacos.getHostname(), nacos.getPort());
    }
}
