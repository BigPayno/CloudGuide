package com.payno.hystrix.controller;

import com.payno.hystrix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author payno
 * @date 2019/11/22 11:44
 * @description
 *      服务降级
 *      @EnableHystrix：开启
 *          Hystrix 断路器
 *      @HystrixCommand：fallbackMethod
 *          指定当该注解标记的方法出现失败、错误时，调用哪一个方法进行优雅的降级返回，
 *          对用户屏蔽错误，做优雅提示。
 */
@RestController
public class Controller {
    @Autowired
    UserService userService;
    @GetMapping("/{userName}")
    public String checkUser(@PathVariable("userName")String userName) throws Exception{
        return userService.getUser(userName);
    }
}
