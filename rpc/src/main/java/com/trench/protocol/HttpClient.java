package com.trench.protocol;

import com.trench.SerializeUtils;
import com.trench.frawork.Invocation;
import com.trench.nacos.dome.NacosHttp;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HttpClient {
    public String send(String hostName, Integer port, Invocation invocation) throws IOException {
        //读取nacos中的配置用户的请求方式。如http   POST get等
        NacosHttp nacosHttp = new NacosHttp();
        try {
            URL url = new URL(nacosHttp.getHttp(), hostName, port, nacosHttp.getFile());
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setRequestMethod(nacosHttp.getRequestMethod());
            httpURLConnection.setDoOutput(true);

            //配置
            OutputStream outputStream = httpURLConnection.getOutputStream();
            ObjectOutputStream oss = new ObjectOutputStream(outputStream);

            oss.writeObject(SerializeUtils.serialize(invocation));
            oss.flush();
            oss.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            return (String) SerializeUtils.deSerialize(IOUtils.toString(inputStream).getBytes(StandardCharsets.UTF_8));
        } catch (MalformedURLException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }
    }
}
