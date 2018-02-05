package com.dongnao.jack.cluster;

import com.dongnao.jack.invoke.Invocation;

/**
 * 集群调用策略
 * @author coderyao
 */
public interface Cluster {

    /**
     * 调用方式
     * @param invocation
     * @return
     * @throws Exception
     */
    String invoke(Invocation invocation) throws Exception;
}
