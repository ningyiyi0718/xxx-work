package com.xxxwork.demo.config;


import com.xxxwork.demo.entity.DataSourceContextHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Author: ElevenYang
 * @Description: 动态数据源类
 * @Date 2023/4/8 16:19
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * 获取当前数据源
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSource();
    }
}
