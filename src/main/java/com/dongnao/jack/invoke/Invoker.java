package com.dongnao.jack.invoke;

/** 
 * @Description 返回String，用json的方式进行通信 
 * @ClassName   Invoker
 * @date        2017年11月11日 下午10:17:19
 * @author      dn-jack
 */

public interface Invoker {

    /**
     * 调用方法
     * @param invocation
     * @return
     * @throws Exception
     */
    String invoke(Invocation invocation) throws Exception;
}
