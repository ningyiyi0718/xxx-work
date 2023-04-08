package com.xxxwork.demo.enums;

/**
 * @Author: ElevenYang
 * @Description: 数据源类型
 * @Date 2023/4/8 16:23
 */
public enum DataSourceEnum {

    PRIMARY("primary"),

    SECOND("second"),

    ;

    private final String value;

    DataSourceEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
