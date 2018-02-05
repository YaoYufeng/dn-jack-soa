package com.dongnao.jack.cluster;

import com.dongnao.jack.invoke.Invocation;
import com.dongnao.jack.invoke.Invoker;

/** 
 * @Description 这个如果调用失败就自动切换到其他集群节点 
 * @ClassName   FailoverClusterInvoke 
 * @date        2017年11月18日 下午9:37:46
 * @author      dn-jack
 */
public class FailoverClusterInvoke implements Cluster {

    @Override
    public String invoke(Invocation invocation) throws Exception {
        
        String retries = invocation.getReference().getRetries();
        Integer retriint = Integer.parseInt(retries);
        
        for (int i = 0; i < retriint; i++) {
            try {
                Invoker invoker = invocation.getInvoker();
                String result = invoker.invoke(invocation);
                return result;
            }
            catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
        
        throw new RuntimeException("retries" + retries + "全部失败！！！！");
    }
    
}
