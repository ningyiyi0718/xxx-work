package com.xxxwork.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxxwork.demo.service.AccountService;
import com.xxxwork.demo.yyy.entity.Account;
import com.xxxwork.demo.yyy.mapper.AccountMapper;
import io.shardingsphere.api.HintManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: ElevenYang
 * @Description:
 * @Date 2023/4/18 22:36
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public int addUserAccount(Integer uid, Integer type, BigDecimal balance) {
        // 强制走主库
        HintManager.getInstance().setMasterRouteOnly();

        Account account = new Account();
        account.setUid(uid);
        account.setType(type);
        account.setBalance(balance);
        account.setCtime(LocalDateTime.now());
        return accountMapper.insert(account);
    }

    @Override
    public List<Account> getAll() {
        QueryWrapper<Account> wrapper = new QueryWrapper<>();
        wrapper.last("limit " + 1);
        wrapper.orderByDesc("id");
        return accountMapper.selectList(wrapper);
    }
}
