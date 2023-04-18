package com.xxxwork.demo.service.impl;

import com.xxxwork.demo.annotations.DataSourceSwitch;
import com.xxxwork.demo.xxx.entity.User;
import com.xxxwork.demo.xxx.entity.UserAddress;
import com.xxxwork.demo.enums.DataSourceEnum;
import com.xxxwork.demo.xxx.mapper.UserAddressMapper;
import com.xxxwork.demo.xxx.mapper.UserMapper;
import com.xxxwork.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: ElevenYang
 * @Description: 用户类接口实现
 * @Date 2023/4/8 16:48
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Override
    public int addUser(String name, Integer age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        return userMapper.insert(user);
    }

    @DataSourceSwitch(DataSourceEnum.SECOND)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int addUserAndAddress(String name, Integer age) throws Exception {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        int count = userMapper.insert(user);
        if (count != 1) {
            throw new Exception("添加用户失败");
        }

        System.out.println(1/0);

        UserAddress userAddress = new UserAddress();
        userAddress.setUid(user.getId());
        userAddress.setAddress("北京天安门");
        count = userAddressMapper.insert(userAddress);
        if (count != 1) {
            throw new Exception("添加用户地址失败");
        }
        return count;
    }
}
