package com.xxxwork.demo.controller;

import com.xxxwork.demo.result.ResultCode;
import com.xxxwork.demo.result.ResultData;
import com.xxxwork.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ElevenYang
 * @Description:
 * @Date 2023/4/8 16:51
 */
@Slf4j
@RequestMapping
@RestController
public class UserAct {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public Object addUser() throws Exception {
        int count = userService.addUserAndAddress("张三", 10);
        return count == 1 ? ResultData.success() : ResultData.error(ResultCode.UNKNOWN_ERROR);
    }
}
