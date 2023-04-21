package com.xxxwork.demo.service.feign;

import com.xxxwork.demo.entity.FeignMessage;
import org.springframework.stereotype.Service;

/**
 * @Author: ElevenYang
 * @Description: feign请求容错处理
 * @Date 2023/4/19 21:45
 */
@Service
public class FeignMessageFallback implements FeignMessageService {

    @Override
    public Object test(String msg) {
        return "hello";
    }

    @Override
    public void testMessage(FeignMessage message) {

    }
}
