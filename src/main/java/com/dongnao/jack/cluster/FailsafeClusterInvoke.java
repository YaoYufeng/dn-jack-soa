package com.dongnao.jack.cluster;

import com.dongnao.jack.invoke.Invocation;
import com.dongnao.jack.invoke.Invoker;

/** 
 * @Description 调用节点失败，直接忽略 
 * @ClassName   FailsafeClusterInvoke 
 * @date        2017年11月18日 下午9:55:49
 * @author      dn-jack
 */

public class FailsafeClusterInvoke implements Cluster {

    @Override
    public String invoke(Invocation invocation) throws Exception {
        Invoker invoker = invocation.getInvoker();

        try {
            return invoker.invoke(invocation);
        } catch (Exception e) {
            e.printStackTrace();
            return "忽略";
        }
    }

}
