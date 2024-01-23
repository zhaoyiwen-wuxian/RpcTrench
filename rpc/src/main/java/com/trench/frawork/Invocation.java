package com.trench.frawork;

import lombok.Data;

import java.io.Serializable;

@Data
public class Invocation implements Serializable {
    private String interfaceName;
    private String methodName;
    //参数类型
    private Class[] parameterTypes;
    //参数
    private Object[] parameter;

    private String version;

    public Invocation(String interfaceName, String methodName, Class[] parameterTypes, Object[] parameter, String version) {
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.parameterTypes = parameterTypes;
        this.parameter = parameter;
        this.version = version;
    }

    public Invocation(String interfaceName, String methodName, Class[] parameterTypes, Object[] parameter) {
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.parameterTypes = parameterTypes;
        this.parameter = parameter;
    }
}
