package com.payno.feignremoteaccess.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author payno
 * @date 2019/11/25 16:24
 * @description
 */
@Component
public class DelayedHystrixClient implements DelayedClient{
    @Override
    public ResponseEntity<byte[]> test() {
        return null;
    }

    @Override
    public String fileUpload(MultipartFile file) {
        System.out.println("hystrix !!!");
        return "error! this is feign hystrix";
    }
}