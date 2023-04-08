package com.xxxwork.demo.annotations;

import com.xxxwork.demo.enums.DataSourceEnum;

import java.lang.annotation.*;

/**
 * @Author: ElevenYang
 * @Description: 注解驱动数据源
 * @Date 2023/4/8 16:37
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface DataSourceSwitch {

    DataSourceEnum value() default DataSourceEnum.PRIMARY;

}
