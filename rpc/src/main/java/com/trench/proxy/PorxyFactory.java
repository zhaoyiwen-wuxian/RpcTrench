package com.trench.proxy;

import com.trench.MapRemoteRegister;
import com.trench.frawork.Invocation;
import com.trench.loadbalance.LoadBalance;
import com.trench.nacos.dome.Nacos;
import com.trench.protocol.HttpClient;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class PorxyFactory {

    private static final Integer MAX_TIMES = 5;

    public static <T> T getProxy(final Class interfaceClass) {
        //读取配置
        Object proxyInstance = Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Invocation invocation = new Invocation(interfaceClass.getName(), method.getName(), method.getParameterTypes(), args);
                //服务发现
                List<Nacos> nacos = MapRemoteRegister.get(interfaceClass.getName());
                //负载均衡
                AtomicReference<String> send = new AtomicReference<>();
                int times = 0;
                while (times < MAX_TIMES) {
                    Nacos randown = LoadBalance.randown(nacos);
                    try {
                        send.set(new HttpClient().send(randown.getHostname(), randown.getPort(), invocation));
                    } catch (Exception e) {
                        if (times++ >= MAX_TIMES) {
                            throw new RuntimeException();
                        }
                        //容错逻辑
                        send.set("erro");
                    }
                }
                return send.get();
            }
        });
        return (T) proxyInstance;
    }
}
