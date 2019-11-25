package com.payno.hystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;

/**
 * @author payno
 * @date 2019/11/22 11:21
 * @description
 *      @HystrixCommand
 *      方法名必须存在且参数类型、顺序一致
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
