package com.dongnao.jack.configBean;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.dongnao.jack.cluster.Cluster;
import com.dongnao.jack.cluster.FailfastClusterInvoke;
import com.dongnao.jack.cluster.FailoverClusterInvoke;
import com.dongnao.jack.cluster.FailsafeClusterInvoke;
import com.dongnao.jack.invoke.HttpInvoker;
import com.dongnao.jack.invoke.Invoker;
import com.dongnao.jack.invoke.NettyInvoker;
import com.dongnao.jack.invoke.RmiInvoker;
import com.dongnao.jack.loadbalance.LoadBalance;
import com.dongnao.jack.loadbalance.RandomLoadBalance;
import com.dongnao.jack.loadbalance.RoundRobinLoadBalance;
import com.dongnao.jack.proxy.advice.InvokeInvocationHandler;
import com.dongnao.jack.registry.BaseRegistryDelegate;

/**
 * @author coderyao
 */
public class Reference extends BaseConfigBean implements FactoryBean, InitializingBean, ApplicationContextAware {

    private static final long               serialVersionUID = -7704305132143886916L;

    private String                          intf;

    private String                          loadbalance;

    private String                          protocol;

    private String                          cluster;

    private String                          retries;

    private static ApplicationContext       application;

    private Invoker                         invoker;

    private static Map<String, Invoker>     invokes          = new HashMap<String, Invoker>();

    private static Map<String, LoadBalance> loadBalances     = new HashMap<String, LoadBalance>();

    private static Map<String, Cluster>     clusters         = new HashMap<String, Cluster>();

    /** 
     * registryInfo 这个是生产者的多个服务的列表 
     */
    private List<String>                    registryInfo     = new ArrayList<String>();

    static {
        invokes.put("http", new HttpInvoker());
        invokes.put("rmi", new RmiInvoker());
        invokes.put("netty", new NettyInvoker());

        loadBalances.put("romdom", new RandomLoadBalance());
        loadBalances.put("roundrob", new RoundRobinLoadBalance());

        clusters.put("failover", new FailoverClusterInvoke());
        clusters.put("failfast", new FailfastClusterInvoke());
        clusters.put("failsafe", new FailsafeClusterInvoke());
    }

    public List<String> getRegistryInfo() {
        return registryInfo;
    }

    public void setRegistryInfo(List<String> registryInfo) {
        this.registryInfo = registryInfo;
    }

    public Reference() {
        System.out.println("Reference!构造");
    }

    public String getIntf() {
        return intf;
    }

    public void setIntf(String intf) {
        this.intf = intf;
    }

    public String getLoadbalance() {
        return loadbalance;
    }

    public void setLoadbalance(String loadbalance) {
        this.loadbalance = loadbalance;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    @Override
    public Object getObject() throws Exception {
        System.out.println("Reference! getObject");
        if (protocol != null && !"".equals(protocol)) {
            invoker = invokes.get(protocol);
        } else {
            Protocol pro = application.getBean(Protocol.class);
            if (pro != null) {
                invoker = invokes.get(pro.getName());
            } else {
                invoker = invokes.get("http");
            }
        }
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class<?>[] { Class.forName(intf) }, new InvokeInvocationHandler(invoker, this));
    }

    @Override
    public Class getObjectType() {
        try {
            if (intf != null && !"".equals(intf)) {
                return Class.forName(intf);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Reference.application = applicationContext;
    }

    public static ApplicationContext getApplication() {
        return application;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        registryInfo = BaseRegistryDelegate.getRegistry(id, application);
        System.out.println(registryInfo);

        //完成订阅
        //        RedisApi.subsribe("channel" + id, new RedisServerRegistry());
    }

    public static Map<String, LoadBalance> getLoadBalances() {
        return loadBalances;
    }

    public static void setLoadBalances(Map<String, LoadBalance> loadBalances) {
        Reference.loadBalances = loadBalances;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public String getRetries() {
        return retries;
    }

    public void setRetries(String retries) {
        this.retries = retries;
    }

    public static Map<String, Cluster> getClusters() {
        return clusters;
    }

    public static void setClusters(Map<String, Cluster> clusters) {
        Reference.clusters = clusters;
    }

}
