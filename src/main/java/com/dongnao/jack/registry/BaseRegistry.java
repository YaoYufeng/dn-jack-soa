package com.dongnao.jack.registry;

import java.util.List;

import org.springframework.context.ApplicationContext;

/**
 * 注册中心处理类
 * @author coderyao
 */
public interface BaseRegistry {

    boolean registry(String param, ApplicationContext application);

    List<String> getRegistry(String id, ApplicationContext application);
}
