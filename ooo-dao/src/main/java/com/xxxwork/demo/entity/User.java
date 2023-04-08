package com.xxxwork.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @Author: ElevenYang
 * @Description:
 * @Date 2023/4/8 16:40
 */
@Data
@NoArgsConstructor
public class User {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer age;

    private LocalDate ctime;
}
