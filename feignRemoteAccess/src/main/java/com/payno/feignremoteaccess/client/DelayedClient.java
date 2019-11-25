package com.payno.feignremoteaccess.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author payno
 * @date 2019/11/17 14:16
 * @description
 *   @FeignClient：声明为一个 Feign 远程调用
 *   name：给远程调用起个名字
 *   url：指定要调用哪个 url
 *   configuration：指定配置信息
 *   fallback: 断路器
 *   @RequestMapping：如同 SpringMVC 一样调用。
 *   注解必须为 @RequestMapping，不能为组合注解 @GetMapping 等，否则解析不到
 *   必须指定 method，否则会出问题
 *   value 必须指定被调用方的 url，不能包含域名、ip 等
 * 如果添加gz压缩，就是接收byte[],否则就是String
 */
@FeignClient(
        name = "Task",
        configuration = {FeignConfiguration.class,FeignUploadConfiguration.class},
        fallback =DelayedHystrixClient.class
)
public interface DelayedClient {
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<byte[]> test();
    @RequestMapping(value = "/upload",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String fileUpload(MultipartFile file);
}
