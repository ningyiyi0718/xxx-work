package com.xxxwork.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ElevenYang
 * @Description:
 * @Date 2023/4/7 22:43
 */
@Slf4j
@RequestMapping
@RestController
public class IndexAct implements ErrorController {

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public Object hello() {
        log.info("hello word");
        log.error("hello world");
        return "你好";
    }

    /**
     * 自定义error
     * @return
     */
    @Override
    @RequestMapping(value = "error", method = RequestMethod.GET)
    public String getErrorPath() {
        return null;
    }
}
