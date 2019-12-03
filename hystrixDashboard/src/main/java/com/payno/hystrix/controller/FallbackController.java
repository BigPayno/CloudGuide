package com.payno.hystrix.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.payno.hystrix.command.FallbackBadRequestException;
import com.payno.hystrix.command.FallbackOtherException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author payno
 * @date 2019/12/3 10:50
 * @description
 */
@RestController
@RequestMapping("fb/")
public class FallbackController {
    @GetMapping("1")
    public String test(){
        return "1";
    }

    @GetMapping("badRequest")
    public String badRequest(){
        return new FallbackBadRequestException().execute();
    }

    @GetMapping("otherError")
    public String otherError(){
        return new FallbackOtherException().execute();
    }

    @GetMapping(value = "runtimeError")
    @HystrixCommand(fallbackMethod = "fallback")
    public String fallbackMethod(String id){
        throw new RuntimeException("fallback method !");
    }

    public String fallback(String id, Throwable throwable){
        return "this is @HystrixCommand fallback!";
    }
}
