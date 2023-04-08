package com.xxxwork.demo.result;

/**
 * @Author: ElevenYang
 * @Description: 响应code码，按照模块业务分00-00-00
 * @Date 2023/4/8 17:55
 */
public enum ResultCode {

    /**
     * 10代表默认标识
     * 00代表默认业务
     * 01代表错误码
     */
    SUCCESS(0, "成功"),
    UNKNOWN_ERROR(100000, "未知错误"),

    ;

    private final Integer code;

    private final String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
