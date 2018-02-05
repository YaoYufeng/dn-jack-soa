package com.dongnao.jack.cluster;

import com.dongnao.jack.invoke.Invocation;
import com.dongnao.jack.invoke.Invoker;

/** 
 * @Description 这个如果调用节点异常，直接失败 
 * @ClassName   FailfastClusterInvoke 
 * @date        2017年11月18日 下午9:55:23
 * @author      dn-jack
 */

public class FailfastClusterInvoke implements Cluster {

    @Override
    public String invoke(Invocation invocation) throws Exception {
        Invoker invoker = invocation.getInvoker();
        
        try {
            return invoker.invoke(invocation);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
}
