package com.xxxwork.demo.controller;

import com.xxxwork.demo.result.ResultData;
import com.xxxwork.demo.service.AccountService;
import com.xxxwork.demo.yyy.entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: ElevenYang
 * @Description:
 * @Date 2023/4/22 17:11
 */
@Slf4j
@RestController
@RequestMapping("/account")
public class AccountAct {

    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/add")
    public Object addAccount() {
        int count = accountService.addUserAccount(7000, 1031000, new BigDecimal("3.1415926"));
        return ResultData.success();
    }

    @GetMapping(value = "/read")
    public Object readAccount() {
        List<Account> list = accountService.getAll();
        return ResultData.success(list);
    }
}
