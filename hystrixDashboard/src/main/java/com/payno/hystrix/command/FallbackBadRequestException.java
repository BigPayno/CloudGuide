package com.payno.hystrix.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.exception.HystrixBadRequestException;

/**
 * @author payno
 * @date 2019/12/3 10:46
 * @description
 */
public class FallbackBadRequestException extends HystrixCommand<String> {
    public FallbackBadRequestException(){
        super(HystrixCommandGroupKey.Factory.asKey("GroupBadRequestException"));
    }
    @Override
    protected String getFallback() {
        System.out.println("Fallback 错误信息：" + getFailedExecutionException().getMessage());
        return "this is HystrixBadRequestException Fallback method!";
    }

    @Override
    protected String run() throws Exception {
        /**
         * 应该模拟feign调用
         */
        throw new HystrixBadRequestException("this is HystrixBadRequestException！");
    }
}
