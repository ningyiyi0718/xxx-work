package com.xxxwork.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @Author: ElevenYang
 * @Description: 用户地址
 * @Date 2023/4/8 17:35
 */
@Data
@NoArgsConstructor
public class UserAddress {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer uid;

    private String address;

    private LocalDate ctime;
}
