package com.payno.delayedtask.task;

import com.google.common.reflect.Invokable;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Method;

/**
 * @author payno
 * @date 2019/11/17 10:15
 * @description
 */
@Data
@AllArgsConstructor
public class TaskDomain<T,R> {
    private String taskServiceBeanName;
    private Invokable<T,R> taskMethod;
    private Class<T> bean;
    private Class<?> param;
}
