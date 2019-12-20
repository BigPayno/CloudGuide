package com.payno.h.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * @author payno
 * @date 2019/12/3 10:53
 * @description
 */
public class FallbackOtherException extends HystrixCommand<String> {
    public FallbackOtherException(){
        super(HystrixCommandGroupKey.Factory.asKey("GroupBadRequestException"));
    }

    @Override
    protected String getFallback() {
        return "something error";
    }

    @Override
    protected String run() throws Exception {
        return "hello";
    }
}
