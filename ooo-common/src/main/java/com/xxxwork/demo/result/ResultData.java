package com.xxxwork.demo.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: ElevenYang
 * @Description: 错误信息
 * @Date 2023/4/8 18:00
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultData {

    private String code;

    private String msg;

    private Object data;

    public static ResultData success() {
        return ResultData.builder()
                .code(ResultCode.SUCCESS.getCode().toString())
                .msg(ResultCode.SUCCESS.getMsg())
                .build();
    }

    public static ResultData success(Object data) {
        return ResultData.builder()
                .code(ResultCode.SUCCESS.getCode().toString())
                .msg(ResultCode.SUCCESS.getMsg())
                .data(data)
                .build();
    }

    public static ResultData error(ResultCode resultCode) {
        return ResultData.builder()
                .code(resultCode.getCode().toString())
                .msg(resultCode.getMsg())
                .build();
    }
}
