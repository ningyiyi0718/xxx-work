package com.xxxwork.demo.service;

import com.xxxwork.demo.yyy.entity.Account;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: ElevenYang
 * @Description:
 * @Date 2023/4/18 22:35
 */
public interface AccountService {
    int addUserAccount(Integer uid, Integer type, BigDecimal balance);

    List<Account> getAll();
}
