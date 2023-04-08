package com.xxxwork.demo.config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.xxxwork.demo.enums.DataSourceEnum;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ElevenYang
 * @Description: mybatis-plus多数据源配置
 * 配置步骤：
 * 1.配置yml中多数据源
 * 2.新建enum类维护数据源类型
 * 3.新建context类维护每个线程的数据源类型
 * 4.新建动态数据源类继承AbstractRoutingDataSource
 * 5.新建mybatis配置类，读取yml数据源，并配置动态数据源
 * 6.新建aspect切面，实现动态数据源，最终DynamicDataSource会拿到切面设置的数据源进行请求
 *
 * @Date 2023/4/8 16:14
 */
@Configuration
@MapperScan("com.xxxwork.demo.mapper")
public class MybatisPlusConfig {

    /**
     * 第一个数据源
     *
     * @return
     */
    @Bean(name = "primary")
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource dataSourcePri() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 第二个数据源
     *
     * @return
     */
    @Bean(name = "second")
    @ConfigurationProperties(prefix = "spring.datasource.second")
    public DataSource dataSourceSec() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 动态数据源配置
     *
     * @return
     */
    @Bean
    @Primary
    public DataSource multipleDataSource(@Qualifier("primary") DataSource primary, @Qualifier("second") DataSource second) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> dataSources = new HashMap<>();
        dataSources.put(DataSourceEnum.PRIMARY.getValue(), primary);
        dataSources.put(DataSourceEnum.SECOND.getValue(), second);
        dynamicDataSource.setTargetDataSources(dataSources);
        // 设置默认primary为主数据源
        dynamicDataSource.setDefaultTargetDataSource(primary);
        return dynamicDataSource;
    }

    /**
     * sqlSessionFactory配置
     *
     * @return
     * @throws Exception
     */
    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        // 导入mybatissqlsession配置
        MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
        // 指明数据源
        sessionFactory.setDataSource(multipleDataSource(dataSourcePri(), dataSourceSec()));
        // 指明实体扫描(多个package用逗号或者分号分隔)
        sessionFactory.setTypeAliasesPackage("com.xxxwork.demo.entity");
        // 导入mybatis配置
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(false);
        sessionFactory.setConfiguration(configuration);
        return sessionFactory.getObject();
    }

}
