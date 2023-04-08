package com.xxxwork.demo.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: ElevenYang
 * @Description: 用户token鉴权
 * @Date 2023/4/8 10:57
 */
@Slf4j
@Component
public class AuthTokenGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            log.info("=========================================");
            log.info("AuthTokenGatewayFilterFactory");
            log.info("=========================================");
            String token = exchange.getRequest().getHeaders().getFirst("xxx-work-center-token");
            log.info("AuthTokenGatewayFilterFactory get user token: {}", token);
            return chain.filter(exchange);
        };
    }

    // 没有参数 - AuthToken
    // 添加参数 - AuthToken=xxx-work-center-token
    static class Config {

    }
}
