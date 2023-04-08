package com.xxxwork.demo.entity;

import com.xxxwork.demo.enums.DataSourceEnum;

/**
 * @Author: ElevenYang
 * @Description: 维护当前持有数据源
 * @Date 2023/4/8 16:20
 */
public class DataSourceContextHolder {

    private static final ThreadLocal<String> CONTEXT = new ThreadLocal<String>();


    /**
     * 设置数据源
     * @param dataSource
     */
    public static void setDataSource(DataSourceEnum dataSource) {
        CONTEXT.set(dataSource.getValue());
    }

    /**
     * 获取数据源
     * @return
     */
    public static String getDataSource() {
        return CONTEXT.get();
    }

    /**
     * 移除数据源
     */
    public static void clearDataSource() {
        CONTEXT.remove();
    }
}
