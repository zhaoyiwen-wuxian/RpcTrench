package com.trench.protocol;

import com.trench.frawork.Invocation;
import com.trench.register.LocalRegister;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

public class HttpServerHandler extends HttpServer {
    public void handler(HttpServletRequest request, HttpServletResponse response) {
        //可自行添加为心跳监听等操作
        //处理请求
        try {
            Invocation invocation = (Invocation) new ObjectInputStream(request.getInputStream()).readObject();
            String interfaceName = invocation.getInterfaceName();//接口名称
            Class aClass = LocalRegister.get(interfaceName, invocation.getVersion());

            Method method = aClass.getMethod(invocation.getMethodName(), invocation.getParameterTypes());
            String invoke = (String) method.invoke(aClass.newInstance(), invocation.getParameter());

            IOUtils.write(invoke.getBytes(StandardCharsets.UTF_8), response.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
