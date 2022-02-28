package com.hzg.jmx;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;

/**
 * @Package: com.hzg.jmx
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2022-02-11 13:32
 */
public class HelloAgent {
    public static void main(String[] args) throws Exception {
        // 通过工厂类获取MBeanServer，用来做MBean的容器
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        // ObjectName的取名格式："域名:name=MBean名称"，其中的域名和MBean可以自定义命名
        ObjectName objectName = new ObjectName("jmxBean:name=hello");
        // 将Hello这个类注入到MBeanServer中，注入需要创建一个ObjectName类
        mBeanServer.registerMBean(new Hello(), objectName);
        TimeUnit.SECONDS.sleep(60 * 60);
    }
}
