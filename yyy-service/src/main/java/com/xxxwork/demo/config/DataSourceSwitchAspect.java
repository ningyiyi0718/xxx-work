package com.xxxwork.demo.config;

import com.xxxwork.demo.annotations.DataSourceSwitch;
import com.xxxwork.demo.enums.DataSourceEnum;
import com.xxxwork.demo.entity.DataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * 动态数据源切面
 */
@Slf4j
@Component
@Aspect
@Order(-100) // 值越小，越优先加载
public class DataSourceSwitchAspect {

    @Pointcut("execution(* com.xxxwork.demo.service..*.*(..))")
    private void dbAspect() {
    }

    /**
     * 前置增强
     * @param joinPoint
     */
    @Before("dbAspect()")
    public void before(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        DataSourceSwitch dataSourceSwitch = methodSignature.getMethod().getAnnotation(DataSourceSwitch.class);
        if (ObjectUtils.isEmpty(dataSourceSwitch) || ObjectUtils.isEmpty(dataSourceSwitch.value())) {
            log.info("================default datasource: {}================", DataSourceEnum.PRIMARY.getValue());
            DataSourceContextHolder.setDataSource(DataSourceEnum.PRIMARY);
            return;
        }

        log.info("================set datasource: {}================", dataSourceSwitch.value().getValue());
        // 动态路由数据源
        switch (dataSourceSwitch.value().getValue()) {
            case "primary":
                DataSourceContextHolder.setDataSource(DataSourceEnum.PRIMARY);
                break;
            case "second":
                DataSourceContextHolder.setDataSource(DataSourceEnum.SECOND);
                break;
            default:
                DataSourceContextHolder.setDataSource(DataSourceEnum.PRIMARY);
        }
    }

    /**
     * 后置增强
     * @param joinPoint
     */
    @After("dbAspect()")
    public void after(JoinPoint joinPoint) {
        log.info("================clear datasource================");
        DataSourceContextHolder.clearDataSource();
    }

}