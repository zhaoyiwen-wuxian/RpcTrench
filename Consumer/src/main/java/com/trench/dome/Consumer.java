package com.trench.dome;

import com.trench.proxy.PorxyFactory;
import com.trench.server.HelloService;

public class Consumer {
    public static void main(String[] args) {
        HelloService helloService = PorxyFactory.getProxy(HelloService.class);
        String trench = helloService.say("trench");
        System.out.println(trench);
    }
}
