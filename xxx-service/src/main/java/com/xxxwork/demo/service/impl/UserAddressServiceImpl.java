package com.xxxwork.demo.service.impl;

import com.xxxwork.demo.service.UserAddressService;
import com.xxxwork.demo.xxx.entity.UserAddress;
import com.xxxwork.demo.xxx.mapper.UserAddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @Author: ElevenYang
 * @Description:
 * @Date 2023/4/18 22:54
 */
@Service
public class UserAddressServiceImpl implements UserAddressService {

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Override
    public int addUserAddress(Integer uid, String address) {
        UserAddress userAddress = new UserAddress();
        userAddress.setUid(uid);
        userAddress.setAddress(address);
        userAddress.setCtime(LocalDateTime.now());
        return userAddressMapper.insert(userAddress);
    }
}
