package com.payno.eureka_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
public class EurekaClientApplication {

    /**
     * Ribbon
     * RandomRule 	随机策略 	随机选择 Server
     * RoundRobinRule 	轮询策略 	按照顺序循环选择 Server
     * RetryRule 	重试策略 	在一个配置时间段内，当选择的 Server 不成功，则一直尝试选择一个可用的 Server
     * BestAvailableRule 	最低并发策略 	逐个考察 Server，如果 Server 的断路器被打开，则忽略，在不被忽略的 Server 中选择并发连接最低的 Server
     * AvailabilityFilteringRule 	可用过滤测试 	过滤掉一直连接失败，并被标记未 circuit tripped（即不可用） 的 Server，过滤掉高并发的 Server
     * ResponseTimeWeightedRule 	响应时间加权策略 	根据 Server 的响应时间分配权重，响应时间越长，权重越低，被选择到的几率就越低
     * ZoneAvoidanceRule 	区域权衡策略 	综合判断 Server 所在区域的性能和 Server 的可用性轮询选择 Server，并判定一个 AWS Zone 的运行性能是否可用，剔除不可用的 Zone 中的所有 Server
     */
    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
    }

}
