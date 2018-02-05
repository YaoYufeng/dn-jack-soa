package com.dongnao.jack.invoke;

import java.lang.reflect.Method;

import com.dongnao.jack.configBean.Reference;

/**
 * 调用信息
 * @author coderyao
 */
public class Invocation {

    /**
     * 调用的方法
     */
    private Method method;

    /**
     * 调用的入参
     */
    private Object[] objs;
    
    private Invoker invoker;
    
    private Reference reference;
    
    public Method getMethod() {
        return method;
    }
    
    public void setMethod(Method method) {
        this.method = method;
    }
    
    public Object[] getObjs() {
        return objs;
    }
    
    public void setObjs(Object[] objs) {
        this.objs = objs;
    }
    
    public Reference getReference() {
        return reference;
    }
    
    public void setReference(Reference reference) {
        this.reference = reference;
    }
    
    public Invoker getInvoker() {
        return invoker;
    }
    
    public void setInvoker(Invoker invoker) {
        this.invoker = invoker;
    }
    
}
