package com.xxxwork.demo.rocketmqdemo;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @Author: ElevenYang
 * @Description: rocketmq消费者，非手动确认模式
 * @Date 2023/4/9 9:55
 */
@Slf4j
@Component
public class Listener implements RocketMQListener<Object> {

    @Override
    public void onMessage(Object o) {
        log.info("============================================");
        log.info("收到消息了：{}", o.toString());
        log.info("============================================");
    }
}
