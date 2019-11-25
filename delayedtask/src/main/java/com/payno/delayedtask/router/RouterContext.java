package com.payno.delayedtask.router;

import com.google.common.reflect.Invokable;

import java.util.Collection;
import java.util.concurrent.Future;

/**
 * @author payno
 * @date 2019/11/18 11:36
 * @description
 *  T   方法执行的类型
 *  R  方法返回结果类型
 */
public interface RouterContext {
    /**
     * getInvokables
     * @author: payno
     * @time: 2019/11/18 11:46
     * @description: 根据服务类型得到对应的服务
     * @param serviceType
     * @return: java.util.Collection<com.google.common.reflect.Invokable<T,R>>
     */
    <T,R> Collection<Invokable<T,R>> getInvokables(String serviceType);
    /**
     * getInvoker
     * @author: payno
     * @time: 2019/11/18 11:46
     * @description: 通过invokable得到对应的Invoker
     * @param invokable
     * @return: T
     */
    <T,R> T getInvoker(Invokable<T,R> invokable);
    /**
     * invoke
     * @author: payno
     * @time: 2019/11/18 11:47
     * @description: 根据服务执行的策略来得到对应的服务并对结果进行处理并返回
     * @param serviceType
     * @param serviceStrategy
     * @param args
     * @return: R
     */
    <T,R> R invoke(String serviceType,ServiceStrategy serviceStrategy,Object...args);
    /**
     * invokeAsync
     * @author: payno
     * @time: 2019/11/18 11:50
     * @description: 异步执行
     * @param serviceType
     * @param serviceStrategy
     * @param args
     * @return: java.util.concurrent.Future<R>
     */
    <T,R> Future<R> invokeAsync(String serviceType, ServiceStrategy serviceStrategy, Object...args);
}
