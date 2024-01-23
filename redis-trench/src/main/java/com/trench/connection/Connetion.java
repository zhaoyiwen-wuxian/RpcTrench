package com.trench.connection;

import com.trench.enumUtil.RedisRepEnum;
import com.trench.protocol.Protocol;
import com.trench.util.SerializeUtils;
import lombok.Data;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

@Data
public class Connetion {
    private int port;
    private String host;
    private Socket socket;

    private InputStream inputStream;
    private OutputStream outputStream;


    public Connetion(int port, String host) {
        this.port = port;
        this.host = host;
    }

    //链接
    public void comment() {
        try {
            if (!isConnected()) {
                socket = new Socket(host, port);
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Connetion sendCommand(RedisRepEnum redisRepEnum, byte[]... args) {
        comment();
        Protocol.sendCommand(outputStream, redisRepEnum, args);
        return this;
    }

    public boolean isConnected() {
        return socket != null && socket.isBound() && !socket.isClosed() && socket.isConnected() && !socket.isInputShutdown() && !socket.isOutputShutdown();
    }

    public Object getData() {
        byte[] b = new byte[1024];
        try {
            socket.getInputStream().read(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SerializeUtils.deSerialize(b);
    }


}
