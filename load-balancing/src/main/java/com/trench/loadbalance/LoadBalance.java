package com.trench.loadbalance;

import com.trench.nacos.dome.Nacos;

import java.util.List;
import java.util.Random;

public class LoadBalance {
    public static Nacos randown(List<Nacos> ipArrayList) {
        //随机数在list数量中取（1-list.size）
        return ipArrayList.get(new Random().nextInt(ipArrayList.size()));
    }
}
