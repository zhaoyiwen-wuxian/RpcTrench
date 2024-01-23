package com.trench.dome;

import lombok.Data;

import java.io.Serializable;

@Data
public class RedisDemo implements Serializable {

    private String key;

    private Object value;

    public RedisDemo(String key, Object value) {
        this.key = key;
        this.value = value;
    }
}
