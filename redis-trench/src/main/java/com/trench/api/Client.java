package com.trench.api;

import com.trench.connection.Connetion;
import com.trench.enumUtil.RedisRepEnum;
import com.trench.protocol.Protocol;
import com.trench.util.SerializeUtils;
import redis.clients.jedis.BuilderFactory;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;

public class Client {
    Connetion connetion;

    public Client(String host, Integer port) {
        connetion = new Connetion(port, host);
    }

    public void set(final String key, final String values) {
        connetion.sendCommand(RedisRepEnum.SET, key.getBytes(StandardCharsets.UTF_8), SerializeUtils.serialize(values));
    }

    public Object get(final String key) {
        connetion.sendCommand(RedisRepEnum.GET, key.getBytes(StandardCharsets.UTF_8));
        return connetion.getData();
    }

    public void delete(final String key) {
        connetion.sendCommand(RedisRepEnum.GETDEL, key.getBytes(StandardCharsets.UTF_8));
    }

    //封装redis的过期时间
    public void expire(String key, long seconds) {
        connetion.sendCommand(RedisRepEnum.EXISTS, key.getBytes(StandardCharsets.UTF_8), Protocol.toByteArray(seconds));
    }

    //是否存在key
    public boolean exists(final String key) {
        connetion.sendCommand(RedisRepEnum.EXISTS, key.getBytes(StandardCharsets.UTF_8));
        return (Long) connetion.getData() == 1L;
    }

    //查找key中set包含
    public Set<String> keys(final String key) {
        connetion.sendCommand(RedisRepEnum.KEYS, key.getBytes(StandardCharsets.UTF_8));
        return (Set) BuilderFactory.STRING_SET.build((List) connetion.getData());
    }
}
