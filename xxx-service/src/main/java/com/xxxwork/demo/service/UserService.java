package com.xxxwork.demo.service;


/**
 * @Author: ElevenYang
 * @Description: 用户类接口
 * @Date 2023/4/8 16:46
 */
public interface UserService {

    int addUser(String name, Integer age);

    int addUserAndAddress(String name, Integer age) throws Exception;
}
