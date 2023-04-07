package com.xxxwork.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ElevenYang
 * @Description:
 * @Date 2023/4/7 22:38
 */
@RequestMapping
@RestController
public class IndexAct {

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public Object hello() {
        return "hello";
    }
}
