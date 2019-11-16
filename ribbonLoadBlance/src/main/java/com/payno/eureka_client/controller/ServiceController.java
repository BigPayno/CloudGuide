package com.payno.eureka_client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author payno
 * @date 2019/11/17 00:14
 * @description
 */
@RestController
public class ServiceController {
    @Autowired
    DiscoveryClient discoveryClient;
    @Autowired
    RestTemplate restTemplate;
    @GetMapping("/info")
    public List<String> info(){
        return discoveryClient.getServices();
    }

    @GetMapping("/info2")
    public String getInfo() {
        return this.restTemplate.getForEntity("http://Service-Provider/info", String.class).getBody();
    }
}
