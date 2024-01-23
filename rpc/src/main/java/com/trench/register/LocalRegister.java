package com.trench.register;

import java.util.concurrent.ConcurrentHashMap;

//本地注册
public class LocalRegister {
    private static ConcurrentHashMap<String, Class> map = new ConcurrentHashMap<>();

    public static void register(String interfaceName, Class implClass, String version) {
        map.put(interfaceName + version, implClass);
    }

    public static Class get(String interfaceName, String version) {
        return map.get(interfaceName + version);
    }
}
