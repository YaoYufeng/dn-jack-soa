package com.dongnao.jack.loadbalance;

import java.util.List;

/**
 * 负载均衡策略
 * @author coderyao
 */
public interface LoadBalance {

    /**
     * 从给定的注册地址中选择节点信息
     * @param registryInfo
     * @return
     */
    NodeInfo doSelect(List<String> registryInfo);
}
