package com.dongnao.jack.configBean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.dongnao.jack.registry.BaseRegistryDelegate;

/**
 * 服务实体类
 * @author coderyao
 */
public class Service extends BaseConfigBean implements InitializingBean, ApplicationContextAware {

    private static final long         serialVersionUID = -4806018048798790174L;

    private String                    intf;

    private String                    ref;

    private String                    protocol;

    private static ApplicationContext application;

    public static ApplicationContext getApplication() {
        return application;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Service.application = applicationContext;
    }

    public String getIntf() {
        return intf;
    }

    public void setIntf(String intf) {
        this.intf = intf;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        BaseRegistryDelegate.registry(ref, application);

        //        RedisApi.publish("channel" + ref, "这个内容要跟redis里面的节点容易一致");
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
}
