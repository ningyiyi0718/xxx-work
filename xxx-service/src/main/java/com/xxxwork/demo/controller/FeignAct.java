package com.xxxwork.demo.controller;

import com.xxxwork.demo.entity.FeignMessage;
import com.xxxwork.demo.service.feign.FeignMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ElevenYang
 * @Description: 请求feign，
 *
 * TODO 打印feign日志设置
 * TODO 配置：https://blog.csdn.net/Saintmm/article/details/125533249
 *
 * @Date 2023/4/19 21:40
 */
@RestController
public class FeignAct {

    @Autowired
    private FeignMessageService feignMessageService;

    @RequestMapping(value = "/feign", method = RequestMethod.GET)
    public Object feign() throws Exception {
        FeignMessage msg = new FeignMessage("实体类传输");
        feignMessageService.testMessage(msg);
        return feignMessageService.test("你好呀！");
    }

}
