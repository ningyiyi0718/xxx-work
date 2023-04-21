package com.xxxwork.demo.controller;

import com.xxxwork.demo.entity.FeignMessage;
import com.xxxwork.demo.result.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @Author: ElevenYang
 * @Description:
 * @Date 2023/4/19 21:36
 */
@Slf4j
@RestController
@RequestMapping("/feign")
public class FeignMessageAct {

    @GetMapping(value = "/test")
    public Object test(String msg) {
        FeignMessage message = new FeignMessage(msg + UUID.randomUUID().toString());
        return ResultData.success(message);
    }

    @GetMapping(value = "/testMessage")
    public void testMessage(FeignMessage message) {
        log.info("============feign testMessage 获取到了消息：{}", message.getMsg());
    }

}
