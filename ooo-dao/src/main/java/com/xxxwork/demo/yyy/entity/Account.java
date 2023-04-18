package com.xxxwork.demo.yyy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author: ElevenYang
 * @Description: 账户表
 * @Date 2023/4/18 22:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer uid;

    private Integer type;

    private BigDecimal balance;

    private LocalDateTime ctime;
}
