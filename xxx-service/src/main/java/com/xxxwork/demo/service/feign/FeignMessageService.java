package com.xxxwork.demo.service.feign;

import com.xxxwork.demo.entity.FeignMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: ElevenYang
 * @Description: 请求feign数据
 * @Date 2023/4/19 21:39
 */
@FeignClient(value = "yyy-service", fallback = FeignMessageFallback.class)
@Component
public interface FeignMessageService {

    @GetMapping(value = "/feign/test")
    Object test(@RequestParam(value = "msg") String msg);

    @GetMapping(value = "/feign/testMessage")
    void testMessage(@SpringQueryMap FeignMessage message);

}
