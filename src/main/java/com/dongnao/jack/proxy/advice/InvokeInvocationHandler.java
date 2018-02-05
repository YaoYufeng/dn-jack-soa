package com.dongnao.jack.proxy.advice;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.dongnao.jack.cluster.Cluster;
import com.dongnao.jack.configBean.Reference;
import com.dongnao.jack.invoke.Invocation;
import com.dongnao.jack.invoke.Invoker;

/** 
 * @Description InvokeInvocationHandler 这个是一个advice，在这个advice里面就进行了rpc的远程调用
 * rpc：http、rmi、netty
 *  
 * @ClassName   InvokeInvocationHandler 
 * @date        2017年11月11日 下午10:14:51
 * @author      dn-jack
 */

public class InvokeInvocationHandler implements InvocationHandler {

    private Invoker invoker;

    private Reference reference;

    public InvokeInvocationHandler(Invoker invoker, Reference reference) {
        this.invoker = invoker;
        this.reference = reference;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //在这个invoke里面最终要调用多个远程的provider
        System.out.print("已经获取到了代理实例，已经掉到了InvokeInvocationHandler.invoker");
        Invocation invocation = new Invocation();
        invocation.setMethod(method);
        invocation.setObjs(args);
        invocation.setReference(reference);
        invocation.setInvoker(invoker);
        //        String result = invoker.invoker(invocation);
        Cluster cluster = Reference.getClusters().get(reference.getCluster());
        String result = cluster.invoke(invocation);
        return result;
    }
}
