package com.payno.delayedtask.task;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.reflect.Invokable;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Optional;

/**
 * @author payno
 * @date 2019/11/17 09:56
 * @description
 */
@Slf4j
@Component
public class TaskRunner implements ApplicationRunner, ApplicationContextAware {
    private ApplicationContext applicationContext;
    public ImmutableMap<String, TaskDomain> taskServices;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Map<String,Object> services=applicationContext.getBeansWithAnnotation(TaskService.class);
        ImmutableMap.Builder<String,TaskDomain> mapBuilder=ImmutableMap.builder();
        services.forEach((beanName,bean)->{
            ImmutableList.copyOf(bean.getClass().getMethods()).stream()
                    .map(Invokable::from)
                    .forEach(invokable -> {
                        if(invokable.isAnnotationPresent(TaskMapping.class)){
                            TaskMapping taskMapping=invokable.getAnnotation(TaskMapping.class);
                            mapBuilder.put(
                                    taskMapping.serviceId(),
                                    new TaskDomain(beanName,invokable,bean.getClass(),taskMapping.param()));
                            log.info("LoadingTaskInfo=>{}::{},Param[{}],Return[{}]",beanName,invokable.getName(),taskMapping.param(),invokable.getReturnType());
                        }
                    });
        });
        taskServices=mapBuilder.build();
    }

    public <T,R> Optional<R> invoke(String serviceId, Object param){
        R result=null;
        if(taskServices.containsKey(serviceId)){
            TaskDomain taskDomain=taskServices.get(serviceId);
            Invokable<T,R> invokable=taskDomain.getTaskMethod();
            if(param.getClass() == taskDomain.getParam()){
                try {
                    Class<T> tClass=taskDomain.getBean();
                    T bean= applicationContext.getBean(tClass);
                    result = invokable.invoke(bean, param);
                }catch (InvocationTargetException | BeansException |IllegalAccessException e){
                    e.printStackTrace();
                }
            }
        }
        return Optional.ofNullable(result);
    }
}
