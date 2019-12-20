package com.payno.feignremoteaccess;

import com.payno.feignremoteaccess.client.FeignConfiguration;
import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * Feign 的特性：
 *
 *     可插拔的注解支持
 *     可插拔的 HTTP 编码器、解码器
 *     支持 Hystrix 断路器、Fallback
 *     支持 Ribbon 负载均衡
 *     支持 HTTP 请求、响应压缩
 *  必须配置FeignClients扫描FeignClient
 *      在启用时，会进行包扫描，扫描所有的 @FeignClient 的注解的类，并将这些信息注入 Spring IOC 容器，当定义的 Feign 接口中的方法被调用时，通过 JDK 的代理方式，来生成具体的 RestTemplate。当生成代理时，Feign 会为每个接口方法创建一个 RestTemplate 对象，该对象封装了 HTTP 请求需要的全部信息，如：参数名、请求方法、header等
 * 然后由 RestTemplate 生成 Request，然后把 Request 交给 Client 处理，这里指的 Client 可以是 JDK 原生的 URLConnection、Apache 的 HTTP Client、OkHttp。最后 Client 被封装到 LoadBalanceClient 类，结合 Ribbon 负载均衡发起服务间的调用。
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@EnableFeignClients(defaultConfiguration = FeignConfiguration.class)
public class FeignRemoteAccessApplication {
    public static void main(String[] args) {
        SpringApplication.run(FeignRemoteAccessApplication.class, args);
    }

}
