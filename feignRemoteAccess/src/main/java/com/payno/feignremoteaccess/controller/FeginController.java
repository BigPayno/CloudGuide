package com.payno.feignremoteaccess.controller;

import com.google.common.io.ByteSource;
import com.payno.feignremoteaccess.client.DelayedClient;
import com.payno.feignremoteaccess.client.FeignUploadClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;

/**
 * @author payno
 * @date 2019/11/17 14:24
 * @description
 */
@RestController
public class FeginController {
    @Autowired
    DelayedClient delayedClient;
    @Autowired
    FeignUploadClient feignUploadClient;
    @GetMapping("/test")
    public String test() throws Exception{
        return ByteSource.wrap(delayedClient.test().getBody()).asCharSource(StandardCharsets.UTF_8).read();
    }

    @PostMapping(value = "/upload",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void upload(@RequestPart(value = "file") MultipartFile file){

        feignUploadClient.uploadFile(file);
    }

    @PostMapping(value = "/upload2",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void upload2(@RequestPart(value = "file") MultipartFile file){
        delayedClient.fileUpload(file);
    }
}
