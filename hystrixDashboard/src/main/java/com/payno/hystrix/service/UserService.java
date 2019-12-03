package com.payno.hystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;

/**
 * @author payno
 * @date 2019/11/22 11:21
 * @description
 * @HystrixCommand
 *      方法名必须存在且参数类型、顺序一致
 *
 * @fallback
 *      Hystrix 的异常处理中，有五种出错情况会被 Fallback 截获，触发 Fallbac
 *      FAILURE：执行失败，抛出异常
 *      TIMEOUT：执行超时
 *      SHORT_CIRCUITED：断路器打开
 *      THREAD_POOL_REJECTED：线程池拒绝
 *      SEMAPHORE_REJECTED：信号量拒绝
 */
@Service
public class UserService implements IUserService{
    @HystrixCommand(fallbackMethod = "defaultUser")
    @Override
    public String getUser(String userName) throws Exception {
        if("root".equals(userName)){
            return userName;
        }
        throw new Exception();
    }

    public String defaultUser(String userName){
        return "payno";
    }
}
