package com.payno.delayedtask.service;

import com.google.common.base.Strings;
import com.payno.delayedtask.task.TaskMapping;
import com.payno.delayedtask.task.TaskService;

/**
 * @author payno
 * @date 2019/11/17 10:34
 * @description
 */
@TaskService
public class TestService {
    @TaskMapping(serviceId = "TASK001",param = String.class,result = String.class)
    public String method(String string){
        return Strings.repeat(string,2);
    }
}
