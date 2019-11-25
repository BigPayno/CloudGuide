package com.payno.feignremoteaccess.client;

import feign.Logger;
import feign.codec.Encoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

/**
 * @author payno
 * @date 2019/11/17 14:20
 * @description
 * name    指定 FeignClient 的名称，如果使用到了 Eureka，且使用了 Ribbon 负载均衡，则 name 为被调用者的微服务名称，用于服务发现
 * url 	一般用于调试，可以手动指定 feign 调用的地址
 * decode404 	当 404 时，如果该字段为 true，会调用 decoder 进行解码，否则会抛出 FeignException
 * configuration 	Feign 配置类，可以自定义 Feign 的 Encoder、Decoder、LogLevel、Contract 等
 * fallback 	容错处理类，当远程调用失败、超时时，会调用对应接口的容错逻辑。Fallback 指定的类，必须实现 @FeignClient 标记的接口
 * fallbackFactory 	工厂类，用于生成 fallback 类的示例，可以实现每个接口通用的容错逻辑，减少重复代码
 * path 	定义当前 FeignClient 的统一前缀
 */
@Configuration
public class FeignUploadConfiguration {
    /**
     * 使用feign的注解方式
     *     @Bean
     *     public Contract useFeignAnnotations() {
     *         return new Contract.Default();
     *     }
     */
    /*为FeignConfiguration添加链接eureka的权限
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("seven", "pwd123456");
    }*/
    @Bean
    @Primary
    @Scope("prototype")
    public Encoder multipartEncoder(){
        return new FeignUploadClient.MultiPartEncoder();
    }
}
