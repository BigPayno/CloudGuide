package com.payno.delayedtask.controller;

import com.payno.delayedtask.dto.UserDto;
import com.payno.delayedtask.task.TaskRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

/**
 * @author payno
 * @date 2019/11/17 11:23
 * @description
 */
@RestController
public class TestController {
    @Autowired
    TaskRunner taskRunner;
    @GetMapping("/test")
    public <R> R test(){
        Optional<R> r=taskRunner.invoke("TASK001","abc");
        System.out.println(r.get());
        return r.get();
    }

    @PostMapping(value = "/upload",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void upload(MultipartFile file){
        System.out.println(file.getSize());
    }
}
