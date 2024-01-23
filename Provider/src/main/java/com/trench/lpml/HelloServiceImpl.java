package com.trench.lpml;

import com.trench.server.HelloService;

public class HelloServiceImpl implements HelloService {
    @Override
    public String say(String name) {
        return "hello: " + name;
    }
}
