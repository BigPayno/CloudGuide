package com.payno.eureka.event;

import com.netflix.appinfo.InstanceInfo;
import org.springframework.cloud.netflix.eureka.server.event.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

/**
 * @author payno
 * @date 2019/11/17 01:03
 * @description
 *  对服务事件的监听
 */
@Controller
public class EurekaListener {
    @EventListener
    public void listen(EurekaInstanceCanceledEvent eurekaInstanceCanceledEvent) {
        //服务断线事件
        String appName = eurekaInstanceCanceledEvent.getAppName();
        String serverId = eurekaInstanceCanceledEvent.getServerId();
        System.out.println(appName);
        System.out.println(serverId);
        System.out.println("EurekaInstanceCanceledEvent");

    }

    @EventListener
    public void listen(EurekaInstanceRegisteredEvent event) {
        InstanceInfo instanceInfo = event.getInstanceInfo();
        System.out.println(instanceInfo);

        System.out.println("EurekaInstanceRegisteredEvent");

    }

    @EventListener
    public void listen(EurekaInstanceRenewedEvent event) {
        event.getAppName();
        event.getServerId();
        System.out.println("EurekaInstanceRenewedEvent");


    }

    @EventListener
    public void listen(EurekaRegistryAvailableEvent event) {
        System.out.println("EurekaRegistryAvailableEvent");

    }

    @EventListener
    public void listen(EurekaServerStartedEvent event) {

        System.out.println("EurekaServerStartedEvent");
        //Server启动
    }
}
