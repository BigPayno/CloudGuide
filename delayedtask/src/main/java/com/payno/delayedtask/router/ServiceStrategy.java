package com.payno.delayedtask.router;

import com.google.common.reflect.Invokable;

import java.util.Collection;

/**
 * @author payno
 * @date 2019/11/18 11:40
 * @description
 *      对多个invoke进行筛选得到想要的结果
 */
public interface ServiceStrategy {
    /**
     * filter
     * @author: payno
     * @time: 2019/11/18 11:49
     * @description:
     * @param invokables
     * @return: com.google.common.reflect.Invokable
     */
    Invokable filter(Collection<Invokable> invokables);
}
