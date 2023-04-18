package com.xxxwork.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author: ElevenYang
 * @Description: 事务消息的内容
 * @Date 2023/4/18 22:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransMessage {

    /**
     * 唯一标识，防止幂等
     */
    private String transId;

    private Integer uid;

    private String address;

    private Integer type;

    private BigDecimal balance;

    private LocalDateTime ctime;
}
