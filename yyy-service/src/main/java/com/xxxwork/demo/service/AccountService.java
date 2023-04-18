package com.xxxwork.demo.service;

import java.math.BigDecimal; /**
 * @Author: ElevenYang
 * @Description:
 * @Date 2023/4/18 22:35
 */
public interface AccountService {
    int addUserAccount(Integer uid, Integer type, BigDecimal balance);
}
