package com.trench.protocol;

import com.trench.enumUtil.RedisRepEnum;
import redis.clients.jedis.util.SafeEncoder;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Protocol {

    public static final String DOLLAR = "$";

    public static final String STAR = "*";

    public static final String BLANK = "\r\n";

    public static void sendCommand(OutputStream outputStream, RedisRepEnum redisRepEnum, byte[]... args) {
        StringBuffer str = new StringBuffer();
        str.append(STAR).append(args.length - 1).append(BLANK);
        str.append(DOLLAR).append(redisRepEnum.name().length()).append(BLANK);
        str.append(redisRepEnum).append(BLANK);

        Arrays.stream(args).forEach(arg -> {
            str.append(DOLLAR).append(arg.length).append(BLANK);
            str.append(new String(arg)).append(BLANK);
        });
        try {
            outputStream.write(str.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final byte[] toByteArray(long value) {
        return SafeEncoder.encode(String.valueOf(value));
    }
}
