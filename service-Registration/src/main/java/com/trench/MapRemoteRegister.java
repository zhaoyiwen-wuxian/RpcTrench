package com.trench;

import com.trench.nacos.dome.Nacos;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class MapRemoteRegister {
    private static ConcurrentHashMap<String, List<Nacos>> map = new ConcurrentHashMap<>();

    public static void register(String interfaceName, Nacos nacos) {
        List<Nacos> nacos1 = map.get(interfaceName);
        if (CollectionUtils.isEmpty(nacos1)) {
            nacos1 = new ArrayList<>();
        }
        nacos1.add(nacos);
        map.put(interfaceName, nacos1);
    }

    public static List<Nacos> get(String interfaceName) {
        return map.get(interfaceName);
    }

}
