package com.xxxwork.demo.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import reactor.core.publisher.Mono;

/**
 * @Author: ElevenYang
 * @Description: 统计请求访问时间（全局过滤器）
 * @Date 2023/4/8 10:46
 */
@Slf4j
@Configuration
public class RequestTimeFilter {

    @Bean
    @Order(-100)
    public GlobalFilter requestTime() {
        return (exchange, chain) -> {
            Long startTime = System.currentTimeMillis();
            return chain.filter(exchange).then().then(Mono.fromRunnable(() -> {
                Long endTime = System.currentTimeMillis();
                log.info("request '{}' cost time millis: {}", exchange.getRequest().getURI().getRawPath(), endTime - startTime);
            }));
        };
    }

}
